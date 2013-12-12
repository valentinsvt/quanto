

class IncludesTagLib {
    def includeJQuery = { atr->

	def archivo = atr.archivo ? "<script type='text/javascript' src='${createLinkTo(dir:'js/aplicacion', file: atr.archivo+'.js')}' ></script>" : ""

	def theme = atr.tema ?: "tema"

        out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/js', file:'jquery-1.4.2.min.js')}' ></script>"

        out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/js', file:'jquery-ui-1.8.1.custom.min.js')}' ></script>"
        out << "<link rel='stylesheet' href='${createLinkTo(dir:'js/jquery/css/'+theme, file:'jquery-ui-1.8.1.custom.css')}' />"
        out << "<link rel='stylesheet' href='${createLinkTo(dir:'js/jquery/css/'+theme, file:'jquery.contextmenu.css')}' />"
        // out << "<link rel='stylesheet' href='${createLinkTo(dir:'css', file:'custom.css')}' />"
        // out << "<link rel='stylesheet' href='${createLinkTo(dir:'css', file:'botones.css')}' />"

        out << archivo
        //out << "<script type='text/javascript' src='${createLinkTo(dir:'js/custom', file: 'funciones.js')}' ></script>"
        out << "<script type='text/javascript' src='${createLinkTo(dir:'js/custom', file:'botones.js')}' ></script>"

	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.livequery.js')}' ></script>"
	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.blockui.js')}' ></script>"
	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.cookie.js')}' ></script>"
        out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.contextmenu.js')}' ></script>"
    }

    def jqueryForm = {
	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.jgrowl.js')}' ></script>"
	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.dataTables.js')}' ></script>"
	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.validate.js')}' ></script>"
	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.maskedinput.js')}' ></script>"
	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.calculator.js')}' ></script>"

	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/plugins', file:'jquery.validate.validaciones.js')}' ></script>"

	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/i18n', file:'datepicker_es.js')}' ></script>"
	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/i18n', file:'validate_es.js')}' ></script>"
	out << "<script type='text/javascript' src='${createLinkTo(dir:'js/jquery/i18n', file:'calculator_es.js')}' ></script>"

	out << "<link rel='stylesheet' href='${createLinkTo(dir:'js/jquery/css', file:'jquery.dataTables.css')}' />"
	out << "<link rel='stylesheet' href='${createLinkTo(dir:'js/jquery/css', file:'jquery.jgrowl.css')}' />"
	out << "<link rel='stylesheet' href='${createLinkTo(dir:'js/jquery/css', file:'jquery.calculator.css')}' />"
    }

    def buscador={atr->
        def name=atr.name ? atr.name:" "
        def value=atr.value
        def campos        
        campos=atr.campos
        def controlador=atr.controlador
        def accion=atr.accion
        out<<'<div class=" filaEntera ui-corner-all barra"  > '        
        out<<' '+name+': <input type="text" disabled> <input id="buscar" type="button" value="Buscar" class="tbbtn fg-button fondo ui-corner-all"><input type="hidden" name="materia" value="'+value+'">'
        out<<'</div>'

        out<<' <div class="buscador">'
        out<<'<form class="buscadorForm">'
        out<<'<div class="filaBuscador">'
        out<<'<div id="campo" style="float: left; margin-right:  5px;">'
        out<<' <select name="campo" id="campo" style="width: 130px;" >'
        def i=0
        campos.each{
            if(i==0)
            out<<' <option value="'+it.key+'" tipo="'+it.value.get(1)+'" selected>'+it.value.get(0)+'</option>'
            else
            out<<' <option value="'+it.key+'" tipo="'+it.value.get(1)+'">'+it.value.get(0)+'</option>'
            i++
        }
        out<<'</select>'
        out<<'</div>'
        out<<'<div id="operador" style="float: left; margin-right: 5px;">'
        out<<'<select name="operador" style="width: 130px;"></select>'        
        out<<'</div>'
        out<<'<input type="hidden" name="tipoCampo" id="tipoCampo" value="string">'
        out<<'Criterio:<input type="text" size="14" style="margin-right:5px" name="criterio"><input id="buscarDialog" type="button" value="Buscar" class="tbbtn fg-button fondo ui-corner-all" ></div><div class="contenidoBuscador ui-widget-content ui-corner-all" id="contenidoBuscador"></div></form></div>'
       

        out << "<script type='text/javascript' src='${createLinkTo(dir:'js/aplicacion', file: 'buscador.js')}' ></script>"
        out<<"<script type='text/javascript'>"
        out<<'$(function() {'
        out<<'function enviar() {'
        if(controlador)
        out<<'$.ajax({type: "POST",url: "'+controlador+'/'+accion+'",data: $(".buscadorForm").serialize(),'
        else
        out<<'$.ajax({type: "POST",url: "'+accion+'",data: $(".buscadorForm").serialize(),'
        out<<'success: function(msg){'
        out<<'$(".contenidoBuscador").html(msg).show("slide");'
        out<<'}'
        out<<'});'
        out<<'};'
        out<<'cambiaOperador();'
        out<<'$("#buscarDialog").click(function(){'
        out<<' enviar();'
        out<<'});'
        out<<'function cambiaOperador(){'
        out<<'if($("#campo :selected").attr("tipo")=="string"){'
        out<<'$("#operador").html(\'<select name="operador" style="width: 130px;"><option value="%like%">Contiene</option><option value="like%">Empieza</option><option value="=">Igual</option>  </select>\');'
        out<<'$("#tipoCampo").val("string");'
        out<<'}'
        out<<'if($("#campo :selected").attr("tipo")=="number"){'
        out<<' $("#operador").html(\'<select name="operador" style="width: 130px;"><option value="=">Igual</option><option value="<">Mayor</option><option value=">">Menor</option>  </select>\');'
        out<<'$("#tipoCampo").val("number");'
        out<<'}'
        out<<'if($("#campo :selected").attr("tipo")=="-1")'
        out<<'$("#operador").html(\'<select name="operador" style="width: 130px;"></select>\');'
        out<<'}'
        out<<'});'
        out<<'</script>'   

        
        
    }

}
