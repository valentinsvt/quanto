import Connection
import db
class EncuestasController {

    def index = {redirect(action:'encuesta') }
    static Connection cn = null

    def encuestaFlow={

        inicio{
            action{
                if(session.tipo=="X")
                    return salir()
                //println "---------------------------------------INICIA ENCUESTA--------------------------------------------------------------------------"
                //println "modulo " + session.modulo
                //println "materias " + session.materias
                flow.mapa=["materia":["Materia","string"],"codigo":["Codigo","number"],"campoExtra":["CMEXTRA","string"]]
                //arreglo en el que se cargan las respuestas posibles a la pregunta
                flow.respuestas=[]
                //arreglo en el que se cargan los items relacionados a la pregunta
                flow.items=[]
                //bandera para determinar si es la primera pregunta y no subir el valor de la variable actual en el estado procesarDatos
                flow.start=true
                //mapa donde se guardan temporalmente las sentencias sql de insercion
                flow.inserts=[:]
                //arreglo que guarda las preguntas con respuestas con muchos items
                flow.multiples=[]
                //mapa que contiene las respuestas seleccionadas por el usuario por pregunta. sirve para cargar los datos en los eventos siquite y anterior
                flow.resp=[:]
                //arreglo de las materias seleccionadas
                flow.materias=session.materias
                // println "materias "+flow.materias
                flow.db=new db()
                flow.db.setDB(session.modulo)
                //marca el numero de pregunta donde inicia la encuesta, si el usuario pone guardar esta variable cojera el valor de la pregunta actual
                flow.inicio
                cn = ConnectionFactory.getConnection('Fire')
                flow.connected = cn.connect(flow.db.url, flow.db.driver, flow.db.user, flow.db.pass)
                flow.cedula=session.cedula
                flow.tipo=session.tipo
                flow.fecha=new Date().format("MM/dd/yyyy hh:mm:ss")
                if(!session.esAdmin)
                    flow.condicion=" encu.prsncdla='${flow.cedula}' "
                else{
                    if(!session.esPar)
                        if(session.esDirectivo)
                            flow.condicion=" encu.PROFDRTV='${flow.cedula}' "
                        else
                            flow.condicion=" encu.admncdla='${flow.cedula}' "
                    else{
                        flow.condicion=" encu.prof_par='${flow.cedula}' "
                    }
                }

                flow.rp=[]
                flow.mat=[:]
                flow.listaAdm=[]
                if(session.modulo=="adm" && flow.tipo=="FE"){
                    flow.sql="select admncdla, admnnmbr,admnapll,crgocdgo from admn where dpndcdgo='${session.persona[2]}'"
                    //println "sql depen "+flow.sql
                    cn.getDb().eachRow(flow.sql) { d ->
                        flow.listaAdm.add([d.admncdla.trim(),d.admnnmbr.trim()+" "+d.admnapll.trim()+" crgo "+d.crgocdgo])
                    }
                }
                if(flow.modulo=="prof"){
                    flow.materias.each{
                        flow.sql="select matedscr as des from mate where matecdgo='${it[1]}'"
                        cn.getDb().eachRow(flow.sql) { d ->
                            flow.mat.put(it[1],d.des)
                        }
                    }
                }
                flow.sql="select count(*) as maxpreg from prte where tpencdgo = '${flow.tipo}'"
                cn.getDb().eachRow(flow.sql) { d ->
                    flow.max=d.maxpreg
                }
                //println "max  pregs "+flow.max
                flow.pregunta=""
                flow.pregcdgo=""
                flow.encucdgo=0
                /****************************************************************ENCUCDGO****************************************************************************/
                if(session.modulo=="prof"){
                    if(session.tipoPersona!="P")  {
                        if(session.tipoPersona=="Par"){
                            flow.sql="select encucdgo as cod from encu where prof_par='${flow.cedula}' and tpencdgo='${flow.tipo}' and profcedl='${session.evaluado}' and matecdgo='${session.materia}' and crsocdgo='${session.curso}'  order by encucdgo asc"
                            println "select encu cdgo "+flow.sql
                        }else{
                            if(session.tipoPersona=="Dir"){
                                flow.sql="select encucdgo as cod from encu where PROFDRTV='${flow.cedula}' and tpencdgo='${flow.tipo}' and profcedl='${session.evaluado}' and matecdgo='${session.materia}' and crsocdgo='${session.curso}'  order by encucdgo asc"
                                println "select encu cdgo dir "+flow.sql
                            }else{
                                if(flow.tipo=="FE")
                                    flow.sql="select encucdgo as cod from encu where  estdcdgo='${flow.cedula}' and tpencdgo='${flow.tipo}' order by encucdgo asc"
                                else
                                    flow.sql="select encucdgo as cod from encu where  estdcdgo='${flow.cedula}' and tpencdgo='${flow.tipo}' and matecdgo='${flow.materias[0][1]}' order by encucdgo asc"
                            }
                        }
                    }else{
                        flow.sql="select encucdgo as cod from encu where profcedl='${flow.cedula}' and tpencdgo='${flow.tipo}' order by encucdgo asc"
                    }
                }
                if(session.modulo=="adm") {
                    if(session.tpin=="ext"){
                        if(flow.tipo!="FE")
                            flow.sql="select encucdgo as cod from encu where "+flow.condicion+" and tpencdgo='${flow.tipo}' and admnbnfi='${session.adm[0]}' order by encucdgo asc"
                        else
                            flow.sql="select encucdgo as cod from encu where "+flow.condicion+" and tpencdgo ='FE' order by encucdgo asc"
                    }else{
                        flow.sql="select encucdgo as cod from encu where "+flow.condicion+" and admnbnfi='${session.adm[0]}' order by encucdgo asc"
                    }

                }
                if(session.modulo=="ins") {
                    flow.sql="select encucdgo as cod from encu where "+flow.condicion+" and tpencdgo='${flow.tipo}'  order by encucdgo asc"
                }
                //println "sql ss!! este "+flow.sql
                cn.getDb().eachRow(flow.sql) { d ->
                    flow.encucdgo=d.cod
                }

                println "encu encontro  "+ flow.encucdgo
                if(flow.encucdgo!=0){
                    //flow.sql="select count(*) as co from dtec,encu where encu.encucdgo=dtec.encucdgo and encu.encucdgo=${flow.encucdgo}"
                    flow.sql="select first 1 prte.PRTENMRO as  co from dtec , prte where prte.pregcdgo=dtec.pregcdgo and dtec.ENCUCDGO=${flow.encucdgo} and prte.TPENCDGO=DTEC.TPENCDGO order by prte.PRTENMRO desc"
                    //println "num preg "+flow.sql
                    def num=0
                    cn.getDb().eachRow(flow.sql) { d ->
                        num=d.co
                    }
                    //println "num tipo encu"+num
                    if(flow.max<=num){
                        flow.encucdgo=0
                        if(session.modulo=="ins" || session.esPar )
                            return salir()
                    }
                }
                //println "encucdgo comen "+flow.encucdgo
                if(flow.encucdgo==0){
                    flow.sql="select Gen_id(encu,1) as codigo  from dual;"
                    cn.getDb().eachRow(flow.sql) { d ->
                        flow.encucdgo=d.codigo
                    }

                    //println "encucdgo "+flow.encucdgo
                    // println "  flow.materia "+flow.materias[0]
                    def sqlInsert
                    if(flow.tipo=="FE"){
                        sqlInsert="insert into encu (encucdgo,tpencdgo,estdcdgo,encufcha) values (${flow.encucdgo},'${flow.tipo}','${flow.cedula}','${flow.fecha}')"
                    }
                    if(flow.tipo=="DC") {
                        sqlInsert="insert into encu (encucdgo,tpencdgo,profcedl,encufcha,matecdgo,crsocdgo,dctaprll,tpifcdgo,estdcdgo) values (${flow.encucdgo},'${flow.tipo}','${flow.materias[0][0]}','${flow.fecha}','${flow.materias[0][1]}','${flow.materias[0][2]}','${flow.materias[0][3]}','E','${flow.cedula}')"
                    }
                    if(flow.tipo=="AD") {
                        sqlInsert="insert into encu (encucdgo,tpencdgo,profcedl,encufcha,tpifcdgo) values (${flow.encucdgo},'${flow.tipo}','${flow.cedula}','${flow.fecha}','P')"
                    }
                    if(flow.tipo=="PR") {
                        sqlInsert="insert into encu (encucdgo,tpencdgo,profcedl,encufcha,prof_par,matecdgo,crsocdgo) values (${flow.encucdgo},'${flow.tipo}','${session.evaluado}','${flow.fecha}','${flow.cedula}','${session.materia}','${session.curso}')"
                        //println "tipo pr "+sqlInsert

                    }
                    if(flow.tipo=="DI") {
                        sqlInsert="insert into encu (encucdgo,tpencdgo,profcedl,encufcha,PROFDRTV,matecdgo,crsocdgo) values (${flow.encucdgo},'${flow.tipo}','${session.evaluado}','${flow.fecha}','${flow.cedula}','${session.materia}','${session.curso}')"
                        //println "tipo pr "+sqlInsert

                    }
                    if(session.modulo=="adm"){
                        //println "adm "+session.esAdmin
                        if(session.tpin=="ext" || session.tpin=="auto"){
                            if(flow.tipo!="FE")
                                sqlInsert="insert into encu (encucdgo,tpencdgo,crgocdgo,admnbnfi,${((session.esAdmin)?'admncdla':'prsncdla')},encufcha) values (${flow.encucdgo},'${flow.tipo}','${session.adm[1]}','${session.adm[0]}','${flow.cedula}','${flow.fecha}')"
                            else
                                sqlInsert="insert into encu (encucdgo,${((session.esAdmin)?'admncdla':'prsncdla')},encufcha,tpencdgo) values (${flow.encucdgo},'${flow.cedula}','${flow.fecha}','FE')"
                        }
                    }
                    if(session.modulo=="ins"){
                        //println "inst "
                        sqlInsert="insert into encu (encucdgo,${((session.esAdmin)?'admncdla':'prsncdla')},encufcha,tpencdgo,tpprcdgo) values (${flow.encucdgo},'${flow.cedula}','${flow.fecha}','${flow.tipo}'${((session.esAdmin)?",'"+session.persona[6]+"'":",'"+session.persona[1]+"'")})"
                    }
                    println " sqlinsert encu!!!!! "+sqlInsert
                    cn.execSql(sqlInsert)
                    flow.actual=1
                    flow.inicio=1
                }
                else{
                    flow.sql="select count(*) as actual from  dtec where encucdgo=${flow.encucdgo}"
                    // println "sql cpunt "+flow.sql
                    cn.getDb().eachRow(flow.sql) { d ->
                        //println "each "+d
                        flow.actual=d.actual
                        flow.inicio=d.actual
                    }
                    if(flow.actual==0){
                        flow.actual=1
                        flow.inicio=1
                    }else{
                        flow.actual++
                        flow.inicio++
                        flow.start=true
                    }
                    //println "actual ini "+flow.actual

                }
                /**************************************************************************************ENCUCDGO********************************************************************************************************/
                cn.disconnect();
                return success()
            }
            on("success").to "procesarDatos"
            on("salir"){flow.mensaje="Usted ha superado el numero de encuestas permitidas"}.to "confirmar"
        }
        preguntaTipo1{
            on("siguiente"){
                switch (session.modulo){

                    case "prof":
                        flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${flow.items[0][1]},'${params.respuestas.trim()}','${flow.tipo.trim()}')"
                        break;
                    case "adm":
                        // ,'${session.adm[0]}'
                        flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${flow.items[0][1]},'${params.respuestas.trim()}','${flow.tipo.trim()}')"
                        break;
                    case "ins":
                        flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${flow.items[0][1]},'${params.respuestas.trim()}','${flow.tipo.trim()}')"
                        break;

                }
                flow.inserts.put(flow.pregcdgo,flow.sqlInsert)
                flow.resp.put(flow.pregcdgo,params.respuestas)
                //println "insert 1"+flow.sqlInsert
            }.to "guardar"
            on("anterior"){
                flow.actual=flow.actual-2
                if(flow.actual<1){
                    flow.actual=1
                    flow.start=true
                }
            }.to("procesarDatos")
            on("guardar"){}.to ("guardar")
            on("abortar"){
                flow.respuestas=[]
                flow.items=[]
                flow.start=true
                flow.inserts=[]
                flow.actual=flow.inicio
            }.to("confirmar")

        }
        preguntaTipo2{
            on("siguiente"){
                flow.materias.each{
                    if(it[1]==params.materia)
                        flow.ms=it
                }
                switch (session.modulo){

                    case "prof":
                        //println "llega de materia: ${params.materia}"
                        //println "flow items: " + flow.items
                        //println flow.ms
                        flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,matecdgo,tpencdgo,profcedl,crsocdgo,dctaprll)values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${flow.items[0][1]},'${params.respuestas.trim()}',${(params.respuestas.trim()!="NI")?"'"+params.materia.trim()+"'":'null'},'${flow.tipo.trim()}',${(params.respuestas.trim()!="NI")?"'"+flow.ms[0]+"'":'null'},${(params.respuestas.trim()!="NI")?"'"+flow.ms[2]+"'":'null'},${(params.respuestas.trim()!="NI")?flow.ms[3]:'null'})"
                        break;
                    case "adm":break;
                    case "ins":break;

                }

                flow.inserts.put(flow.pregcdgo,flow.sqlInsert)
                def temp=[]
                temp.add(params.respuestas)
                temp.add(params.materia)
                flow.resp.put(flow.pregcdgo,temp)
                // println "insert "+flow.sqlInsert

            }.to "guardar"
            on("anterior"){
                flow.actual=flow.actual-2
                if(flow.actual<1){
                    flow.actual=1
                    flow.start=true
                }
            }.to("procesarDatos")
            on("guardar"){}.to ("guardar")
            on("abortar"){
                flow.respuestas=[]
                flow.items=[]
                flow.start=true
                flow.inserts=[]
                flow.actual=flow.inicio
            }.to("confirmar")

        }
        preguntaTipo3{
            on("siguiente"){
                //println "SIGUIENTE"
                if(params.num.toInteger()>1){
                    def i=0
                    def temp=[]
                    def arr=[]
                    flow.multiples.add(flow.pregcdgo)
                    params.seleccionados.each{
                        //println "aqui"
                        switch (session.modulo){
                            case "prof":
                                // flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo,profcedl,matecdgo,crsocdgo,dctaprll) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${it.trim()},'${params.respuestasSelec[i].trim()}','${flow.tipo.trim()}','${flow.materias[0][0]}','${flow.materias[0][1]}','${flow.materias[0][2]}','${flow.materias[0][3]}')"
                                flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${it.trim()},${((it.trim()!="0")?"'"+params.respuestasSelec[i].trim()+"'":'null')},'${flow.tipo.trim()}')"
                                break;
                            case "adm":
                                flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${it.trim()},${((it.trim()!="0")?"'"+params.respuestasSelec[i].trim()+"'":'null')},'${flow.tipo.trim()}')"
                                break;
                            case "ins":
                                flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${it.trim()},${((it.trim()!="0")?"'"+params.respuestasSelec[i].trim()+"'":'null')},'${flow.tipo.trim()}')"
                                break;
                        }
                        temp.add(flow.sqlInsert)
                        arr.add([it,params.respuestasSelec[i].trim()])
                        //println "insert "+flow.sqlInsert
                        i++
                    }
                    flow.resp.put(flow.pregcdgo,arr)
                    flow.inserts.put(flow.pregcdgo,temp)
                }
                else{
                    //println " ACA"
                    def temp=[]
                    switch (session.modulo){
                        case "prof":
                            //flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo,profcedl,matecdgo,crsocdgo,dctaprll) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${params.seleccionados.trim()},'${params.respuestasSelec.trim()}','${flow.tipo.trim()}','${flow.materias[0][0]}','${flow.materias[0][1]}','${flow.materias[0][2]}','${flow.materias[0][3]}')"
                            flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${params.seleccionados.trim()},${((params.seleccionados.trim()!="0")?"'"+params.respuestasSelec.trim()+"'":'null')},'${flow.tipo.trim()}')"
                            break;
                        case "adm":
                            flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${params.seleccionados.trim()},${((params.seleccionados.trim()!="0")?"'"+params.respuestasSelec.trim()+"'":'null')},'${flow.tipo.trim()}')"
                            break;
                        case "ins":
                            flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${params.seleccionados.trim()},${((params.seleccionados.trim()!="0")?"'"+params.respuestasSelec.trim()+"'":'null')},'${flow.tipo.trim()}')"
                            break;

                    }
                    flow.inserts.put(flow.pregcdgo,flow.sqlInsert)
                    temp.add(params.seleccionados.trim())
                    temp.add(params.respuestasSelec.trim())
                    def arr=[]
                    arr.add(temp)
                    flow.resp.put(flow.pregcdgo,arr)
                    //println  "sql insert3 "+flow.sqlInsert
                }
            }.to "guardar"
            on("anterior"){
                flow.actual=flow.actual-2
                if(flow.actual<1){
                    flow.actual=1
                    flow.start=true
                }
            }.to("procesarDatos")

            on("guardar"){}.to ("guardar")
            on("abortar"){
                flow.respuestas=[]
                flow.items=[]
                flow.start=true
                flow.inserts=[]
                flow.actual=flow.inicio
            }.to("confirmar")

        }
        preguntaTipo4{
            on("siguiente"){
                switch (session.modulo){

                    case "prof":
                        break;
                    case "adm":
                        flow.sqlInsert="insert into dtec (encucdgo,pregcdgo,pritcdgo,respcdgo,prcscdgo,admncdla,tpencdgo) values (${flow.encucdgo},'${flow.pregcdgo.trim()}',${flow.items[0][1]},'${params.respuestas.trim()}',${(params.respuestas.trim()!="NI")?"'"+params.proc.trim()+"'":'null'},${(params.respuestas.trim()!="NI")?"'"+params.admn.trim()+"'":'null'},'${flow.tipo}')"
                        //println "sql 4  "+flow.sqlInsert
                        break;
                    case "ins":break;

                }

                flow.inserts.put(flow.pregcdgo,flow.sqlInsert)
                def temp=[]
                temp.add(params.respuestas)
                temp.add(params.proc)
                temp.add(params.admn)
                flow.resp.put(flow.pregcdgo,temp)
                // println "insert "+flow.sqlInsert

            }.to "procesarDatos"
            on("anterior"){
                flow.actual=flow.actual-2
                if(flow.actual<1){
                    flow.actual=1
                    flow.start=true
                }
            }.to("procesarDatos")
            on("guardar"){}.to ("guardar")
            on("abortar"){
                flow.respuestas=[]
                flow.items=[]
                flow.start=true
                flow.inserts=[]
                flow.actual=flow.inicio
            }.to("confirmar")

        }
        guardar{
            action{
                cn = ConnectionFactory.getConnection('Fire')
                switch (session.modulo){
                    case "prof":
                        flow.connected = cn.connect(flow.db.url, flow.db.driver, flow.db.user, flow.db.pass)
                        break;
                    case "adm":
                        flow.connected = cn.connect(flow.db.urlAdm, flow.db.driverAdm, flow.db.userAdm, flow.db.passAdm)
                        break;
                    case "ins":
                        flow.connected = cn.connect(flow.db.urlIns, flow.db.driverIns, flow.db.userIns, flow.db.passIns)
                        break;
                }
                // println "inserts Guardar"+flow.inserts
                flow.inserts.each{
                    if(flow.multiples.contains(it.key)){
                        it.value.each{d->
                            //println " d " +d
                            def sq=d
                            sq=verifica(sq,cn)
                            cn.execSql(sq)
                            cn.getDb().execute(d)
                        }
                    }
                    else{
                        //println " it.value  "+it.value
                        def sq=it.value
                        sq=verifica(sq,cn)
                        cn.execSql(sq)
                        //cn.getDb().execute(it.value)
                    }
                }
                flow.inicio=flow.actual
//                flow.start=true
                flow.inserts=[:]
                flow.multiples=[]

                if(flow.actual<flow.max)
                    return success()
                else
                    return fin()
            }
            on("success"){
                cn.disconnect();
            }.to "procesarDatos"
            on("fin"){
                completaEncuesta(flow.encucdgo,cn)
                cn.disconnect();
                flow.mensaje="Gracias por su colaboración"
            }.to "confirmar"
        }

        procesarDatos{
            action{
                cn = ConnectionFactory.getConnection('Fire')
                flow.connected = cn.connect(flow.db.url, flow.db.driver, flow.db.user, flow.db.pass)
                //println "max "+flow.max+" actual "+flow.actual+"  start "+flow.starts
                if(flow.actual>flow.max && flow.start==true){

                    return salir()

                }
                if(!flow.start){
                    //println "entro start"
                    flow.actual++
                    flow.anterior=true
                }
                else
                    flow.anterior=false
                // println " resp "+flow.resp
                println " actual "+flow.actual+" inicio "+flow.inicio+"  start  "+flow.start
                flow.start=false
                flow.items=[]
                flow.rp=[]
                flow.sql="select pregdscr as des, preg.pregcdgo as cod, pregnmrp as num from preg, prte where preg.pregcdgo = prte.pregcdgo and tpencdgo = '${flow.tipo}' and prtenmro  = ${flow.actual}"
                //  println " sql  pregunta "+flow.sql
                cn.getDb().eachRow(flow.sql) { d ->
                    //println "d "+d
                    flow.pregunta=d.des.trim()
                    flow.pregcdgo=d.cod.trim()
                    flow.maxItem=d.pregnmrp
                }
                //println " datos pregunta "+flow.pregunta+ " codigo: "+flow.pregcdgo
                flow.sql="select respdscr, resp.respcdgo from rppg, resp where pregcdgo = '${flow.pregcdgo}' and resp.respcdgo = rppg.respcdgo "
                //println "sql  resp "+flow.sql
                cn.getDb().eachRow(flow.sql) { d ->
                    flow.rp.add([d.respcdgo.trim(),d.respdscr.trim()])
                }
                //println "respuestas "+flow.rp
                flow.sql="select PREGCDGO,PRITCDGO,PRITDSCR from prit where pregcdgo='${flow.pregcdgo}' order by PRITCDGO "
                //println "sql "+flow.sql
                cn.getDb().eachRow(flow.sql) { d ->
                    flow.items.add([d.PREGCDGO.trim(),d.PRITCDGO,d.PRITDSCR])
                }
                //println " items--------------\n "+flow.items+ " --------- "+flow.items.size()
                if(flow.items.size()<2){
                    flow.tipoPreg=1
                    if(flow.rp[0].contains("ASG") )
                        flow.tipoPreg=2
                    if(flow.rp[1].contains("PRO"))
                        flow.tipoPreg=4
                }
                else
                    flow.tipoPreg=3
                if(flow.actual>flow.max)
                    flow.view="guardar"
                else{
                    switch ( flow.tipoPreg) {
                        case 1:
                            flow.view="preguntaTipo1"
                            break;
                        case 2:
                            flow.view="preguntaTipo2"
                            break;
                        case 3:
                            flow.view="preguntaTipo3"
                            break;
                        case 4:
                            flow.view="preguntaTipo4"
                            break;
                    }
                }
                cn.disconnect();
                return success()
            }
            on ("success").to "redirect"
            on("salir"){

                if(session.tipoPersona=="P")
                    flow.mensaje="usted ya realizo su autoevaluación"

            }.to "confirmar"
        }

        redirect{
            action{
                yes()
            }
            on('yes').to { flow.view }
        }

        confirmar{

        }

    }

    def completaEncuesta(encu,cn){
        println "completa encuesta "+encu
        def sql ="update encu set encuetdo='C' where encucdgo='${encu}' and encuetdo is null"
        println "sql "+sql
        cn.getDb().execute(sql.toString())
        return
    }

    def listaProcesos={
        def adm=params.adm
        def db=new db()
        db.setDB(session.modulo)
        def tx='<div class="respRadio" >'
        cn = ConnectionFactory.getConnection('Fire')
        def connected = cn.connect(db.url, db.driver, db.user, db.pass)
        def sql="select prcs.prcscdgo,prcs.prcsdscr from admn,prcs,crgo,prcg where admn.admncdla='${adm}' and crgo.crgocdgo=admn.crgocdgo and crgo.crgocdgo=prcg.crgocdgo and prcg.prcscdgo=prcs.prcscdgo"
        //println "sql ajax "+sql
        def i=0
        cn.getDb().eachRow(sql) { d ->
            //println " d "+d
            tx+='<div style="margin-top: 5px; class="rr"><input style="float: left;" type="radio" class="fg-buttonset-single radioMat rr"  id="'+d.prcscdgo+'" value="'+d.prcscdgo+'" name="respMat" '+((i==0)? 'checked':' ')+' ><div class="fila '+((i%2==0)?'fondoFila':'')+' item"  style="width: 385px;margin-bottom: 5px;margin-left: 20px; " id="div_'+d.prcscdgo+'">'+d.prcsdscr.trim()+'</div></div>'
            i++
        }
        tx+=' </div>'
        render(tx)

    }

    def verifica(sql,cn){
        def partsOrg = sql.split("values")
        def parts2
        def sets=""
        def tupla =null
//        println "parts 1 "+partsOrg
        if(partsOrg.size()>1){
            parts2=partsOrg[1].split("\\(")
//            println "parts 2 "+parts2
            parts2=parts2[1].split(",")
//            println "parts 3 "+parts2
            def tx = "select * from dtec where encucdgo=${parts2[0]} and pregcdgo=${parts2[1]};"
//            println "sql verifica "+tx
            cn.getDb().eachRow(tx.toString()) { d ->
                tupla=d
            }
            if(tupla!=null){
                def campos=[]
                def valores=[]
//                println "es update "+tupla
                def updt = "update dtec set & where encucdgo=${parts2[0]} and pregcdgo=${parts2[1]}"
                partsOrg.eachWithIndex {p,i->
                    def parts= p.split("\\(")
                    if(parts.size()==2){
                        if(i==0)
                            campos = parts[1].split(",")
                        else
                            valores = parts[1].split(",")
                    }
                }
                campos.eachWithIndex {c,i->
                    sets+=c.replaceAll("\\)","")+"="
                    sets+=valores[i].replaceAll("\\)","")
                    if(i<campos.size()-1)
                        sets+=","

                }
//                println "valores "+valores
//                println "campos "+campos
//                println "sets "+sets
                updt=updt.replaceAll("&",sets)
//                println "update final "+updt
                return updt
            }else{
                return sql
            }
        }
    }


}