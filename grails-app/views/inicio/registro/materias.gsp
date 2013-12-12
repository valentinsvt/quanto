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
  <div class="contenido ui-widget-content ui-corner-all" style="height: 570px; overflow: auto">
    <div style="height: 120px; margin-bottom: 5px;"><g:render template="../layouts/cabecera"/></div>
      <br>
      <div class="ui-widget-header" style="margin-bottom: -20px;">${flujo}</div>
      <br>
        
      <div class="list">
        <g:form action="registro" method="post" event="siguiente">
          <div class="filaEntera ui-corner-all borde izq min" style="height: 300px;overflow: auto; margin-bottom: 5px;">
          <table>
            <thead>
              <tr>
                <th><h4>(*)</h4></th>
                <th><h4>Materia</h4></th>
                <th><h4>Profesor</h4></th>
                <th><h4>Curso</h4></th>
                <th><h4>Paralelo</h4></th>
              </tr>
            </thead>
            <tbody>
            <g:each in="${lista}" status="i" var="d">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td><input type="checkbox" name="cdgo"
                           value="${d[0].encodeAsHTML()}"></td>
                <td>${d[1]?.encodeAsHTML()}</td>
                <td>${d[2]?.encodeAsHTML()}</td>
                <td>${d[3]?.encodeAsHTML()}</td>
                <td>${d[4]?.encodeAsHTML()}</td>
              </tr>
            </g:each>
            </tbody>
          </table>
          </div>
          <g:link controller="inicio" action="registro" event="anterior" class="btn">Anterior &nbsp;&nbsp;&nbsp;&nbsp;</g:link>
          <g:link controller="inicio" action="registro" event="cancelar" class="btn">Cancelar &nbsp;&nbsp;&nbsp;</g:link>
          <input id="siguiente" type="submit" class="botones2 btn" value="Aceptar Materias y Continuar" >
        </g:form>
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
                  alert("por favor seleccione por lo menos una materia");
                  event.preventDefault();
               }
           });
        });

  </script>


</body>
</html>