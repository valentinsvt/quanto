<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'login.css')}" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'quanto.ico')}" type="image/x-icon" />
    %{--<script type="text/javascript" src="${resource(dir:'js/jquery/js',file:'jquery-1.4.2.min.js')}"></script>--}%
    <title>Quanto</title>

</head>
<body>
<div class="contenido ui-widget-content ui-corner-all " style="height: 570px; overflow: auto;margin-top: 30px;">
    <div class="logo" style="margin-left: 20px;"><img src="${resource(dir:'images',file:'universidad.jpg')}"></div>
    <div class="logo_tx" style="margin-left: 170px;width: 500px">
        <h1>${message(code: 'universidad', default: 'Tedein S.A. - Pruebas')}</h1>
        <br/>Sistema para uso exclusivo de la esta Universidad
    </div>
    <div style="float: right; margin-top: -20px;"> <a href="${createLink(controller: 'inicio', action: 'inicio')}" id="fin" class="btn">Abandonar la Encuesta</a></div>
    <div class=" filaEntera ui-corner-all " >
       <h3>Seleccione al profesor que desea evaluar</h3>
    </div>
    <div style="width: 90%;margin: auto;margin-top: 10px;height: 330px;border: 1px solid black;overflow: auto" class="ui-corner-all">
        <table class="ui-corner-all">
            <thead>
            <tr>
                <th>Apellido</th>
                <th>Nombre</th>
                <th>Matéria</th>
                <th>Curso</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${opciones}" var="p" status="i">
                <tr class="${(i%2!=0)?'odd':'even'}">
                    <td>${p['profapll']}</td>
                    <td>${p['profnmbr']}</td>
                    <td>${p['matedscr']}</td>
                    <td>${p['crsodscr']}</td>
                    <td><a href="#" class="boton eval btn" cedula="${p['profcedl']}" materia="${p['matecdgo']}" curso="${p['crsocdgo']}" >Evaluar</a> </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</div>
%{--<div style=" width: 130px;height: 45px;"><a href="${createLink(controller: 'inicio', action: 'inicio')}" id="fin" class="btn"><font size="5">Finalizar</font></a></div>--}%

<script type="text/javascript">
    $(".eval").button().click(function(){
        var cedula=$(this).attr("cedula")
        var materia=$(this).attr("materia")
        var curso=$(this).attr("curso")
        $.ajax({
            type: "POST",
            url: "${g.createLink(action: 'prepEncuestaPares')}",
            data: "cedula="+cedula+"&materia="+materia+"&curso="+curso,
            success: function(msg){
                window.location="${g.createLink(action: 'pantallaDeEspera')}"
            }

        });
    })
</script>
</body>
</html>
