<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta name="layout" content="main" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quanto</title>
  </head>
  <body>
    <div class="contenido ui-widget-content ui-corner-all " style="position: absolute;left: 10px;">

      <div class=" filaEntera ui-corner-all " >
        <div style="float: left"><img src="${resource(dir:'images',file:'quanto.png')}" width="420px" height="318px"/></div>
        Usted está a punto de inicar la Evaluación<br><br>

        Por favor lea  y entienda bien las preguntas y señale la opción de respuesta que más se aplique.<br><br>

        <g:if test="docentes"> Si aun no ha ingresado las materias en las que se halla matriculado, hágalo usando el botón "registrar materias"
        </g:if>
      </div>
      <div style=" width: 130px;height: 45px;position: absolute; bottom: 10px;right: 10px;"><a href="#" id="emp" class="btn"><font size="5">Empezar</font></a></div>
    </div>
  </body>

</html>



