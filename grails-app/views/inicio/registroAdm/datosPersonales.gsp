
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
          Nombre: <input type="text" name="nombre"><br>
          Apellido: <input type="text" name="apellido"><br>
          <div style="width: 100%;height: 40px;margin-top: 15px;">
          <div style="float: left; width: 40px;">Soy:</div>
          <div class="bs" style=" margin-left: 9px;float: left; width: 200px;">
            <input type="radio" name="tipo" id="radio1" value="ES" checked><label for="radio1">Estudiante</label>
            <input type="radio" name="tipo" id="radio2" value="PF"><label for="radio2">Profesor</label>
          </div>
          </div>
        </div>
        <div class=" filaEntera ui-corner-all borde" >
       <div class=" ui-widget-header" id="toggle_"  style="margin: auto;margin-bottom: 10px;height: 15px;padding-top: 0px;background: #124080;color: #eee">
        Escoja su facultad
      </div>
          <g:each in="${dependencias}" var="dep" status="i">
            <div style="margin-right: 5px;height: 20px;background-color: #ddd; margin-bottom: 2px;cursor:pointer;" class="respRadio" id="${i}">
              <div style="float: left; padding-top: 1px;margin-left: 10px;"><input  id="dep_${i}" style="cursor: pointer" type="radio" value="${dep[0]}"  name="dependencias"  class="fg-buttonset-single radio rr" ${(1!=1)? 'checked':' '} ></div>
              <div style="padding-top: 1px; float: left;margin-left: 10px; ">${dep[1]}</div>
            </div>
          </g:each>
        </div>
      </g:form>
      <div class="filaBotones">
        <div class="back">
        </div>
        <div class="abort"><g:link controller="encuestas" action="encuesta" event="abortar" class="btn">Salir</g:link></div>
        <div class="next"> <a href="#" id="siguiente" class="btn">Siguiente</a></div>
      </div>
    </div>
  </body>
</html>
