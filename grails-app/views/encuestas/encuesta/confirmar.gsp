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
    <div class="contenido ui-widget-content ui-corner-all " style="margin: auto;margin-top: 10px; position: relative">
      <div style="position: absolute; left: 60px; top: 50px;"><img src="${resource(dir:'images',file:'q-docentes.jpg')}"/></div>
%{--
      <div style="position: absolute; left: 230px; top: 160px;"><img src="${resource(dir:'images',file:'portadaQDocentes.jpg')}"/></div>
      <div style="position: absolute; left: 60px; top: 290px;"><img src="${resource(dir:'images',file:'portadaQDocentes.jpg')}"/></div>
--}%
      <div class=" span-7  " style="position: absolute;right: 5px; margin-top: 50px; text-align: center" >
        <h1 style="color: #375E89">Gracias por usar el sistema Quanto</h1>
        ${mensaje}
      </div>
      <div style=" width: 130px;height: 45px;position: absolute; bottom: 10px;right: 10px;"><a href="${createLink(controller: 'inicio', action: 'inicio')}" id="fin" class="btn"><font size="5">Finalizar</font></a></div>
    </div>
  </body>
</html>
