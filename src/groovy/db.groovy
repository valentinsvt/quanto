
class db implements Serializable{
    String url 
    String driver
    String user
    String pass
/*Ya no se usa*/
//    String urlDoc = 'jdbc:firebirdsql:172.20.4.6/3050:/db/encu.gdb'  /*  UPEC*/
//    String urlAdm = 'jdbc:firebirdsql:172.20.4.6/3050:/db/admn.gdb'  /*  UPEC*/

//    String urlDoc = 'jdbc:firebirdsql:127.0.0.1/3050:/db/encu.gdb'
//    String urlAdm = 'jdbc:firebirdsql:127.0.0.1/3050:/db/admn.gdb'

    String urlDoc = 'jdbc:firebirdsql:100.0.0.1/3050:/db/encu.gdb'
    String urlAdm = 'jdbc:firebirdsql:100.0.0.1/3050:/db/admn.gdb'

    String driverDoc= 'org.firebirdsql.jdbc.FBDriver'
    String userDoc= 'sysdba'
    String passDoc='admin'

    String driverAdm= 'org.firebirdsql.jdbc.FBDriver'
    String userAdm= 'sysdba'
    String passAdm='admin'

//    String urlIns = 'jdbc:firebirdsql:127.0.0.1/3050:/db/inst.gdb'
//    String urlIns = 'jdbc:firebirdsql:172.20.4.6/3050:/db/inst.gdb'
    String driverIns= 'org.firebirdsql.jdbc.FBDriver'
    String userIns= 'sysdba'
    String passIns='admin'


    void setDB(tipo){
        switch (tipo){
            case "prof" :
            url=urlDoc
            driver=driverDoc
            user=userDoc
            pass=passDoc
            break;
            case "adm" :
            url=urlAdm
            driver=driverAdm
            user=userAdm
            pass=passAdm
            break;
            case "ins" :
            url=urlIns
            driver=driverIns
            user=userIns
            pass=passIns
            break;
        }
    }

	
}

