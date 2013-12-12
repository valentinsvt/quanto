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
                <div style="width: 150px; height: 20px;cursor:pointer; " class="respRadio"><input style="float: left;" type="radio" class="fg-buttonset-single radio rr" value="${respuesta[0]}" name="respuestas" ${(respuesta[0]==resp[pregcdgo]?.get(0))? 'checked':' '} ><div style=" margin-bottom: 5px; height: 15px; width: 140px;margin-left: 20px;">${respuesta[1]}</div></div>
            </g:each>
        </div>
        <div class=" fila2c ui-corner-all borde izq min materias" style="display: none" >
            <g:each in="${materias}" var="m" status="i">
                <div class="respRadio">
                    <input style="float: left;" type="radio" class="fg-buttonset-single radioMat rr"  id="${m[2]}_${i}" value="${m[1]}" name="respMat" ${(i==0)? 'checked':' '} >
                    <div class="fila ${(i%2==0)?'fondoFila':''} item"  style="width: 200px;margin-bottom: 5px;margin-left: 20px;" id="div_${m[2]}_${i}">${m[4]}</div>
                </div>
            </g:each>

        </div>
        <div class=" filaEntera ui-corner-all barra"  >
            Asignatura : <input type="text" disabled id="matVal"> <input id="buscar" type="button" value="Buscar" class="tbbtn fg-button fondo ui-corner-all"><input type="hidden" name="materia" id="materia" value="${resp[pregcdgo]?.get(1)}">
        </div>
    </g:form>
    <div class="filaBotones">
        <div class="back">
            <g:if test="${(anterior==true)}">
                <g:link controller="encuestas" action="encuesta" event="anterior" class="btn">Anterior</g:link>
            </g:if>
        </div>
        <div class="abort"><g:link controller="encuestas" action="encuesta" event="abortar" class="btn">Abortar</g:link></div>
        <div class="save"><g:link controller="encuestas" action="encuesta" event="guardar" class="btn">Guardar</g:link></div>
        <div class="next"> <a href="#" id="siguienteTp2" class="btn">Siguiente</a></div>
    </div>



</div>
</body>
</html>
