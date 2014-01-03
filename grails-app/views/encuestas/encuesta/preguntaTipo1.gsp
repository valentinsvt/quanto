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
<div class="contenido ui-widget-content ui-corner-all " style="margin: auto;margin-top: 10px;">
    <div class=" ui-widget-header " id="toggle_" >
        Pregunta ${actual} de ${max}
    </div>
    <div class="mensaje" style="display: none;"></div>
    <div class=" filaEntera ui-corner-all borde pregunta" >
        ${pregunta}
    </div>
    <g:form action="encuesta" controller="encuestas" event="siguiente" class="formEncuestas">
        <div class=" filaEntera ui-corner-all borde" >
            <g:each in="${rp}" var="respuesta" status="i">
                <div style="margin-right: 5px;height: 20px;background-color: #ddd; margin-bottom: 2px;cursor:pointer;" class="respRadio" id="${i}">
                    <div style="float: left; padding-top: 1px;margin-left: 10px;"><input  id="rr_${i}" style="cursor: pointer" type="radio" value="${respuesta[0]}"  name="respuestas"  class="fg-buttonset-single radio rr" ${(respuesta[0]==resp[pregcdgo])? 'checked':' '} ></div>
                    <div style="padding-top: 1px; float: left;margin-left: 10px; ">${respuesta[1]}</div>
                </div>
            </g:each>
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
        <div class="next"> <a href="#" id="siguiente" class="btn">Siguiente</a></div>
    </div>
</div>
</body>
</html>
