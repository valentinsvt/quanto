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
      <div class="mensaje" style="display: none; font-family: serif"></div>
      <div class="filaEntera ui-corner-all">
        <g:form action="registro" method="post" event="encuesta" class="formEncuestas">
          <!-- <div style="height: 400px; overflow: auto;"> -->
          <input type="hidden" id="tipo" value="${tipo}">
          <input type="hidden" id="numMate" value="${(datos)?datos.size():0}">
          <g:if test="${datos?.size()>0}">
          <div class="filaEntera ui-corner-all borde izq min" style="height: 300px;overflow: auto; margin-bottom: 5px; margin-left: -20px;">
          <table>
            <thead>                                                                      materias
              <tr>
                <th><h4>(*)</h4></th>
                <th><h4>Materia</h4></th>
                <th><h4>Profesor</h4></th>
                <th><h4>Curso</h4></th>
                <th><h4>Paralelo</h4></th>
              </tr>
            </thead>
            <tbody>
            <!-- <hr>Hola ${lista}</hr> -->
            <g:each in="${datos}" status="i" var="d">
              <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td><input type="checkbox" name="cdgo" class="ndm"
                           value="${d[0].encodeAsHTML()} " ></td>
                <td>${d[1]?.encodeAsHTML()}</td>
                <td>${d[2]?.encodeAsHTML()}</td>
                <td>${d[3]?.encodeAsHTML()}</td>
                <td>${d[4]?.encodeAsHTML()}</td>
              </tr>
            </g:each>
            </tbody>
          </table>
          </div>

          %{--<g:link controller="inicio" action="registro" event="eliminar" class="btn"> Eliminar la Materia&nbsp;&nbsp;&nbsp;</g:link>--}%
          </g:if>
          %{--<g:link controller="inicio" action="registro" event="registrar" class="btn"> Registrar Otras Materias &nbsp;&nbsp;&nbsp;</g:link>--}%
          <input id="empEncu" type="button" class="botones2 btn" value="Realizar la Encuesta" >
        </g:form>
      </div>
    </div>
  </center>
</body>
</html>
