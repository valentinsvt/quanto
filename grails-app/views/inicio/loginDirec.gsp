<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'login.css')}" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'quanto.ico')}" type="image/x-icon" />

    <title>Quanto Docentes</title>
</head>
<body>
<script language="JavaScript1.2">
    <!--
    //var nom = navigator.appName;
    //alert(nom);
    //if (nom == "Microsoft Internet Explorer"){
    //window.location="IE";

    //}

    //-->
</script>
<g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
</g:if>

<center>
    <div class="contenedor">
        <div class="logo"><img src="${resource(dir:'images',file:'universidad.jpg')}"></div>
        %{--<div class="logo_tx"><h1>Universidad Nacional de Chimborazo </h1>--}%
        <div class="logo_tx">
            <h1>${message(code: 'universidad', default: 'Tedein S.A. - Pruebas')}</h1>
            <br/>Sistema para uso exclusivo de la esta Universidad
        </div>
        <div class="info">
            <div class="texto">
                <ul>
                    <br>
                    <li>Evaluación de Docentes por los Directivos</li>
                    <br>
                    <li>Identifíquese para registrar la encuesta.</li>
                </ul>
            </div>
            <div class="textoDer">
                <img src="${resource(dir:'images',file:'qencu-dire.jpg')}" >
            </div>
        </div>
    <div class="login">
        <g:form action="loginDirec" method="post">
            <fieldset style="width: 350px; height: 145px; margin-top: 4px; border-color: black;" >
                <legend>Ingrese sus datos personales</legend>
                <div class="fila der " style="margin-top: 10px;">
                    <label>Cédula de Identidad:</label><input class="cedula" type="text" name="cdla" maxlength="10" size="12">
                    <br><span class="error">${msg}</span>
                </div>
                <div class="fila der" style="margin-top: 40px">
                    <input class="submit ui-corner-all" type="submit" value="Ingresar">
                </div>
            </fieldset>
            </div>
        </g:form>
    </div>
    <div class="pie"> Desarrollado por: TEDEIN SA &nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="http://www.tedein.com.ec">www.tedein.com.ec</a></div>

</center>

<script type="text/javascript" src="${resource(dir:'js/jquery/js',file:'jquery-1.4.2.min.js')}"></script>
<script type="text/javascript">

    var j$ = jQuery.noConflict();
    j$(document).ready(function() {

//        j$('.error').hide();
        j$('.submit').click(function(event){
            var vl = j$('.cedula').val();
            var ln = vl.length;
            //alert(vl);
            if((ln != 10)||(parseInt(vl.substring(9,10)) != cedula(vl))) {
                j$('.error').show();
                j$('.cedula').css("border", "1px solid #f00");
                event.preventDefault();
            } else {
                if(parseInt(vl.substring(9,10))==cedula(vl)) {

                } else {
                    j$('.error').hide();
                    event.preventDefault();
                }
            }
        });
    });

    function cedula(ci){
        var i = 1;
        var s = 0;
        var d = 0;
        while (i<10) {
            n = parseInt(ci.substring(i-1,i));
            if(i%2){
                if((n*2) > 9) { s += n*2 -9;} else { s += n*2}
            } else {
                s += n;
            }
            //alert("valor de s:" + s + " >> n:" + n);
            i++;
        }
        d = (90 - s) % 10;
        return d;
    };

</script>


</body>
</html>
