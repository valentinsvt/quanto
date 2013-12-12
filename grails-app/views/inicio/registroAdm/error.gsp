
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>Quanto</title>
  </head>
  <body>
    <div class="contenido ui-widget-content ui-corner-all " style="margin: auto;margin-top: 10px;">
      <div class=" ui-widget-header " id="toggle_" >
      Error
      </div>
      <div class="mensaje" style="display: none;"></div>
      <g:form action="registroAdm" controller="inicio" event="siguiente" class="formEncuestas">

        <div class=" filaEntera ui-corner-all borde" style="height:385px; overflow: auto" id="cuerpo">
       <div class=" ui-widget-header" id="toggle_"  style="margin: auto;margin-bottom: 10px;height: 15px;padding-top: 0px;background: #124080;color: #eee">
        Ha ocurrido un error en el proceso de autentificación
      </div>
          ${mensaje}
        </div>
      </g:form>
      <div class="filaBotones">
        <div class="back">
        </div>
        <div class="abort"><g:link controller="encuestas" action="encuesta" event="abortar" class="btn">Salir</g:link></div>
      </div>
    </div>
  </body>
</html>
