<!--
  To change this template, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <link rel="shortcut icon" href="${resource(dir:'images',file:'quanto.ico')}" type="image/x-icon" />
    <title>Quanto</title>
  </head>
  <body>
    <div class="contenido ui-widget-content ui-corner-all " style="margin: auto;margin-top: 30px; position: relative">
      <div id="1" class="botones " style="position: absolute; left: 100px; top: 70px; width: 245px; height: 167px;z-index: 1"><g:link action="login"  ><div   ><img  class="img_1" src="${resource(dir:'images',file:'qencu.jpg')}" style="border: white;" /></div></g:link></div>
      <div id="2" class="botones " style="position: absolute; left: 430px; top: 70px; width: 246px; height: 167px;z-index: 2"><g:link action="loginAdm" ><div ><img class="img_2" src="${resource(dir:'images',file:'qadmn.jpg')}" style="border: white"/></div></g:link></div>
%{--
      <div id="1" class="botones " style="position: absolute; left: 60px; top: 70px; width: 245px; height: 167px;z-index: 1"><g:link action="login"  ><div   ><img  class="img_1" src="${resource(dir:'images',file:'qencu.jpg')}" style="border: white;" /></div></g:link></div>
      <div id="2" class="botones " style="position: absolute; left: 230px; top: 180px; width: 246px; height: 167px;z-index: 2"><g:link action="loginAdm" ><div ><img class="img_2" src="${resource(dir:'images',file:'qadmn.jpg')}" style="border: white"/></div></g:link></div>
      <div id="3" class="botones " style="position: absolute; left: 60px; top: 310px; width: 245px; height: 163px;z-index: 3"><g:link action="loginIns" ><div ><img class="img_3" src="${resource(dir:'images',file:'qinst.jpg')}" style="border: white"/></div></g:link></div>
--}%
      <div class=" span-8  " style="position: absolute;left: 260px; bottom:  60px; text-align: left; background: #efefe0; padding: 10px;" >
        <div style="color: #375E89; text-align:center;  font-size:32px; margin: 20px;">Quanto Web</div>
        Seleccione el módulo sobre el cual desea realizar su encuesta:<br/><br/>
        <b>Quanto Docentes</b>: Evaluación a Profesores<br/><br/>
          <b>Quanto Administrativos</b>: Evaluación del personal administrativo<br>
%{--          <b>Quanto Intituciones</b>: Evaluación Institucional.<br>--}%
      </div>
    </div>
  <div class="pie"> Desarrollado por: TEDEIN SA &nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="http://www.tedein.com.ec">www.tedein.com.ec</a></div>

  </body>
</html>
