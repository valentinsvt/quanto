<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title>Quanto</title>
  </head>
  <body>
    <div class="contenido ui-widget-content ui-corner-all ">
      <div class=" ui-widget-header" id="toggle_"  style="margin: auto;">
        Pregunta ${actual} de ${max}
      </div>
      <div class="mensaje" style="display: none;"></div>
      <div class=" filaEntera ui-corner-all borde" >
${pregunta}
      </div>
       <g:form action="encuesta" controller="encuestas" event="siguiente" class="formEncuestas">
         <input type="hidden" id="num" name="num" value="${(resp[pregcdgo])? resp[pregcdgo].size() : '0'}">
         <input type="hidden" id="max" value="${maxItem}">
      <div class=" fila2c ui-corner-all borde izq min2" >
        <g:each in="${items}" var="item" status="i">
          <div class="fila ${(i%2==0)?'fondoFila':''} item" id="${item[1]}">${item[2]}</div>
        </g:each>

      </div>
      <div class=" fila2c ui-corner-all borde der min" id="select" style="height: 300px; " ><div class=" ui-widget-header" id="toggle_"  style="margin: auto;margin-bottom: 10px;height: 15px;padding-top: 0px;background: #124080;color: #eee">
        Seleccionados
      </div>
        <g:if test="${resp[pregcdgo]}">
          <g:each in="${resp[pregcdgo]}">
            <div id='cont_${it[0]}' class='selected' item='${it[0]}' style='margin-left:15px;'>
            <input type='hidden' id="resp_${it[0]}" name='respuestasSelec' value='${it[1]}'>
            <input type='hidden' id='item_${it[0]}' name='seleccionados' value='${it[0]}'>
                <div class='fila bordeAbajo'>${items[it[0].toInteger()][2]}</div>
                </div>
          </g:each>
        </g:if>
      </div>
      <div class="dialog" style="display: none; background-color: white; width: 250px"><g:each in="${rp}" var="respuesta" status="i">
          <div style="width: 260px; height: 20px;  "><input style="float: left;" type="radio" class="fg-buttonset-single radio" value="${respuesta[0]}" name="respuestas" ${(i==0)?"checked":""}><div style=" margin-bottom: 5px; height: 15px; width: 230px;margin-left: 20px;">${respuesta[1]}</div></div>
        </g:each></div>
      <input type="hidden" id="resp" value="">
      <div class="filaBotones">
        <div class="back">
        <g:if test="${(anterior==true)}">
            <g:link controller="encuestas" action="encuesta" event="anterior" class="btn">Anterior</g:link>
        </g:if>
          </div>
        <div class="abort"><g:link controller="encuestas" action="encuesta" event="abortar" class="btn">Abortar</g:link></div>
        %{--<div class="save"><g:link controller="encuestas" action="encuesta" event="guardar" class="btn">Guardar</g:link></div>--}%
        <div class="next"> <a href="#" id="siguienteTipo3" class="btn">Siguiente</a></div>
      </div>
       </g:form>
    </div>
  </body>
</html>
