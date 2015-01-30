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
    <div id="1" class="botones " style="position: absolute; left: 100px; top: 40px; width: 245px; height: 167px;z-index: 1">
        <g:link action="login"  ><div>Estudiantes y Autoevaluación Prof.<img  class="img_1" src="${resource(dir:'images',file:'qencu.jpg')}" style="border: white;" width="245px;" height="167px;" /></div></g:link>
    </div>
    <div id="2" class="botones " style="position: absolute; left: 430px; top: 40px; width: 245px; height: 167px;z-index: 2">
        <g:link action="loginAdm" ><div><img class="img_2" src="${resource(dir:'images',file:'qadmn.jpg')}" style="border: white" width="245px;" height="167px;"/></div></g:link>
    </div>
    <div id="3" class="botones " style="position: absolute; left: 100px; top: 240px; width: 245px; height: 167px;z-index: 1">
        <g:link action="loginPares"  ><div>Soy Docente (Pares)<img  class="img_3" src="${resource(dir:'images',file:'qencu-par.jpg')}" style="border: white;" width="245px;" height="167px;" /></div></g:link>
    </div>
    <div id="4" class="botones " style="position: absolute; left: 430px; top: 240px; width: 245px; height: 167px;z-index: 2">
        <g:link action="loginDirec" ><div >Soy Directivo<img class="img_4" src="${resource(dir:'images',file:'qencu-dire.jpg')}" style="border: white" width="245px;" height="167px;" /></div></g:link>
    </div>
    %{--
          <div id="1" class="botones " style="position: absolute; left: 60px; top: 70px; width: 245px; height: 167px;z-index: 1"><g:link action="login"  ><div   ><img  class="img_1" src="${resource(dir:'images',file:'qencu.jpg')}" style="border: white;" /></div></g:link></div>
          <div id="2" class="botones " style="position: absolute; left: 230px; top: 180px; width: 246px; height: 167px;z-index: 2"><g:link action="loginAdm" ><div ><img class="img_2" src="${resource(dir:'images',file:'qadmn.jpg')}" style="border: white"/></div></g:link></div>
          <div id="3" class="botones " style="position: absolute; left: 60px; top: 310px; width: 245px; height: 163px;z-index: 3"><g:link action="loginIns" ><div ><img class="img_3" src="${resource(dir:'images',file:'qinst.jpg')}" style="border: white"/></div></g:link></div>
    --}%
    <div class=" span-8  " style="position: absolute;left: 30px; bottom:  10px; text-align: left;  padding: 10px;width: 90%;padding-top: 0px;" >
        <div style="color: #102e70; text-align:left;  font-size:20px; margin: 10px;border-bottom: 1px solid black;border-bottom-color:#102e70;margin-left: 0px;padding-bottom: 3px;">Quanto Web</div>
        Seleccione el módulo sobre el cual desea realizar su encuesta:<br/>
        <span style="color: #003785"><b>Quanto Docentes</b>: Evaluación de los Estudiantes al Profesor y Autoevaluación de Profesores<br/></span>
        <b>Quanto Administrativos</b>: Evaluación al personal administrativo<br>
        <b>Quanto Docentes - Pares</b>: Evaluación de los Pares a los Profesores <br/>
        <b>Quanto Docentes - Directivos</b>: Evaluación de los Directivos a los Profesores<br>
        %{--          <b>Quanto Intituciones</b>: Evaluación Institucional.<br>--}%
    </div>
</div>
<div class="pie"> Desarrollado por: TEDEIN SA &nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="http://www.tedein.com.ec">www.tedein.com.ec</a>&nbsp;&nbsp;Versión ${message(code: 'version', default: '1.1.0x')}</div>

</body>
</html>
