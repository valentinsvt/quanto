class UrlMappings {
    static mappings = {
      "/$controller/$action?/$id?"{
	      constraints {
			 // apply constraints here
		  }
	  }
      "/"(controller:"inicio",action:"inicio")
	  "500"(view:'/error')
	}
}
