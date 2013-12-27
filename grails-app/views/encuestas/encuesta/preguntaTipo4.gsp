<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>Quanto</title>
  </head>
  <body>
    <div class="contenido ui-widget-content ui-corner-all ">
      <div class=" ui-widget-header " id="toggle_" style="margin: auto;">
        Pregunta ${actual} de ${max}
      </div>
      <g:form action="encuesta" controller="encuestas" event="siguiente" class="formEncuestas">
        <div class="mensaje" style="display: none;"></div>
        <div class="fila3c ui-corner-all min pregunta" >
            ${pregunta}
        </div>
        <div class="fila1c ui-corner-all min"  >
          <g:each in="${rp}" var="respuesta">
            <div style="width: 155px; height: 20px;cursor:pointer;  " class="respRadio"><input style="float: left;" type
            ="radio" class="fg-buttonset-single radio rr" value="${respuesta[0]}" name="respuestas" ${(respuesta[0]==resp[pregcdgo]?.get(0))? 'checked':' '} ><div style=" margin-bottom: 5px; height: 15px; width: 130px;margin-left: 20px; ">${respuesta[1]}</div></div>
          </g:each>
        </div>

        <div class="  ui-corner-all borde  admin" style="display: none;width: 450px;" >
        <div class=" ui-widget-header" id="toggle_"  style="margin: auto;margin-bottom: 10px;height: 15px;padding-top: 0px;background: #124080;color: #eee;">
        Seleccione una persona
        </div><div>
          <select name="admin" id="admin" >
            <option value="0">Seleccione...</option>
            <g:each in="${listaAdm}" var="adm">
              <option value="${adm[0]}">${adm[1]}</option>
            </g:each>
          </select>
        </div>
          <div style=" width: 450px; height: 300px;overflow: auto;padding-top: 10px;" class="" id="admCont">

          </div>
      </div>
        
 <div class=" filaEntera ui-corner-all barra"  >
   Proceso : <input type="text" disabled id="matVal"> <input id="buscarAdm" type="button" value="Buscar" class="tbbtn fg-button fondo ui-corner-all"><input type="hidden" name="proc" id="materia" value="${resp[pregcdgo]?.get(1)}"><input type="hidden" name="admn" id="admn" value="${resp[pregcdgo]?.get(2)}" >
       </div>
      </g:form>
      <div class="filaBotones">
      <div class="back">
        <g:if test="${(anterior==true)}">
            <g:link controller="encuestas" action="encuesta" event="anterior" class="btn">Anterior</g:link>
        </g:if>
          </div>
        <div class="abort"><g:link controller="encuestas" action="encuesta" event="abortar" class="btn">Abortar</g:link></div>
        %{--<div class="save"><g:link controller="encuestas" action="encuesta" event="guardar" class="btn">Guardar</g:link></div>--}%
        <div class="next"> <a href="#" id="siguienteTp2" class="btn">Siguiente</a></div>
      </div>

      

    </div>
  </body>
</html>
