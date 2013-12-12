
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
        Datos personales
      </div>
      <div class="mensaje" style="display: none;"></div>
      <g:form action="registroAdm" controller="inicio" event="siguiente" class="formEncuestas">
        <div class=" filaEntera ui-corner-all borde" >
          <h1 style="color: black">Datos personales:</h1>
          Nombre: <input type="text" name="nombre" id="nombre"><br>
          Apellido: <input type="text" name="apellido" id="apellido"><br>
          <div style="width: 100%;height: 40px;margin-top: 15px;">
          <div style="float: left; width: 40px;">Soy:</div>
          <div class="bs" style=" margin-left: 9px;float: left; width: 400px;">
            <input type="radio" name="tipo" id="radio1" value="ES" checked><label for="radio1">Estudiante</label>
            <input type="radio" name="tipo" id="radio2" value="PF"><label for="radio2">Profesor</label>
            <input type="radio" name="tipo" id="radio3" value="GR"><label for="radio3">Egresado</label>
            <input type="radio" name="tipo" id="radio4" value="IE"><label for="radio4">Autoridad Externa</label>
          </div>
          </div>
        </div>
       
      </g:form>
      <div class="filaBotones">
        <div class="back">
        </div>
        <div class="abort"><g:link controller="encuestas" action="encuesta" event="abortar" class="btn">Salir</g:link></div>
        <div class="next"> <a href="#" id="siguienteDP" class="btn">Siguiente</a></div>
      </div>
    </div>
  </body>
</html>
