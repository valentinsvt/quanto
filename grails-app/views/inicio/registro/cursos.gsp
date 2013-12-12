<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="${resource(dir:'css',file:'login.css')}" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'quanto.ico')}" type="image/x-icon" />
  </head>
  <body>
  <title>Quanto Docentes - Registro de Materias</title>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <center>
    <div class="contenido ui-widget-content ui-corner-all" style="height: 570px; overflow: auto ;">
      <div style="height: 120px; margin-bottom: 30px;"><g:render template="../layouts/cabecera"/></div>
      <div class="" id="parm">
        <g:form action="registro" method="post" event="siguiente">
          <div class="ui-widget-header" id="titl01">Paso 3: Cursos en los toma Materias</div>
          <br>
          <br>
          <div class=" titulo3">${flujo}</div>
          <br>

          <br><h3>Señale todos los cursos en los que toma materias</h3>
          <br>
          <br>
          <div style="text-align: left; padding-left: 250px">
          <g:each in="${lstacmbo}" status="i" var="d">

            <g:checkBox name="curso" value="${d[0]?.encodeAsHTML()}" checked ="false" />&nbsp;&nbsp;${d[1]?.encodeAsHTML()}<br>

          </g:each>
          <br>
          </div>
          <g:link controller="inicio" action="registro" event="anterior" class="btn">< Anterior &nbsp;&nbsp;&nbsp;</g:link>
          <input id ="siguiente" type="submit" class="botones2 btn" value="Siguiente">
        </g:form>
        <div id="ajx_escl"></div>
        <div id="ajx_matr"></div>
      </div>
    </div>
  </center>
  <script type="text/javascript" src="${resource(dir:'js/jquery/js',file:'jquery-1.4.2.min.js')}"></script>
    <script type="text/javascript">
       var j$ = jQuery.noConflict();
       j$(document).ready(function() {

           j$("#siguiente").click(function(event) {
               var contador = 0;

               j$(':checkbox[checked="true"]').each(
                  function(){
                     contador++;
                  });
               if(contador == 0) {
                  alert("por favor seleccione un curso");
                  event.preventDefault();
               } 
           });
        });

  </script>

</body>
</html>