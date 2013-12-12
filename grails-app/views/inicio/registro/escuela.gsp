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
      <div style="height: 120px; margin-bottom: 30px;"><g:render template="../layouts/cabecera"/></div>
      <div class="" id="parm">
        <g:form action="registro" method="post" event="siguiente">
          <div class="ui-widget-header" id="titl01">Paso 2: Seleccione su Escuela</div>
          <br>
          <div class="titulo3">${flujo}</div>
          <br>

          <g:select name="facl" from="${lstacmbo}"/>
          <br>
          <br>
          <g:link controller="inicio" action="registro" event="anterior" class="btn"> Anterior &nbsp;&nbsp;&nbsp;</g:link>
          <input type="submit" class="botones2 btn" value="Siguiente">
        </g:form>
        <div id="ajx_escl"></div>
        <div id="ajx_matr"></div>
      </div>
    </div>

  </center>
</body>
</html>