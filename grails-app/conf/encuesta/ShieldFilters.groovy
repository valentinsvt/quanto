package encuesta

class ShieldFilters {

    def filters = {
        all(controller:'encuestas', action:'*') {
            before = {
                if(!session.cedula){
                    
                    redirect(controller:'inicio',action:'inicio')
                    session.finalize()
                    return false

                } else {

                    return true

                }
            }
        }
        all(controller:'inicio',action:'registro'){
             before = {
                if(!session.cedula){
                    redirect(controller:'inicio',action:'inicio')
                    session.finalize()
                    return false

                } else {

                    return true

                }
            }
        }
        all(controller:'inicio',action:'pantallaDeEspera'){
             before = {
                if(!session.cedula){
                    redirect(controller:'inicio',action:'inicio')
                    session.finalize()
                    return false

                } else {

                    return true

                }
            }
        }
        all(controller:'inicio',action:'abrir'){
             before = {
                if(!session.cedula){
                    redirect(controller:'inicio',action:'inicio')
                    session.finalize()
                    return false

                } else {

                    return true

                }
            }
        }
        all(controller:'inicio',action:'registro'){
             before = {
                if(!session.cedula){
                    redirect(controller:'inicio',action:'inicio')
                    session.finalize()
                    return false

                } else {

                    return true

                }
            }
        }
        all(controller:'inicio',action:'registroAdm'){
             before = {
                if(!session.cedula){
                    redirect(controller:'inicio',action:'inicio')
                    session.finalize()
                    return false

                } else {

                    return true

                }
            }
        }
    }
    
}
