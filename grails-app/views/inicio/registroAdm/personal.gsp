
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
       Personal administrativo
      </div>
      <div class="mensaje" style="display: none;"></div>
      <g:form action="registroAdm" controller="inicio" event="siguiente" class="formEncuestas">
      
        <div class=" filaEntera ui-corner-all borde" style="height:385px; overflow: auto" id="cuerpo">
       <div class=" ui-widget-header" id="toggle_"  style="margin: auto;margin-bottom: 10px;height: 15px;padding-top: 0px;background: #124080;color: #eee">
        Escoja la persona a la que desea evaluar
      </div>
          <g:each in="${admn}" var="adm" status="i">
            <div style="margin-right: 5px;height: 20px;background-color: #ddd; margin-bottom: 2px;cursor:pointer;" class="${(adm[5]!=1)? ' ': 'respRadio'} " id="${i}">
              <div style="float: left; padding-top: 1px;margin-left: 10px;"><input  id="adm_${i}" style="cursor: pointer" type="radio" value="${adm[0]}:${adm[1]}"  name="personal"  class="fg-buttonset-single radio rr" ${(1!=1)? 'checked':' '} ${(adm[5]!=1)? 'DISABLED': ' '}></div>
              <div style="padding-top: 1px; float: left;margin-left: 10px; ">${adm[2]+" "+adm[3]+" <b>("+adm[4]+")</b>"}</div>
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
