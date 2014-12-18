import org.codehaus.groovy.grails.commons.ApplicationAttributes

class BootStrap {

     def dbConnectionService
     def dataSource

    def init = { servletContext ->
        def ctx=servletContext.getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
        def dataSource = ctx.dataSourceUnproxied
        dataSource.setMinEvictableIdleTimeMillis(1000 * 60 * 30)
        dataSource.setTimeBetweenEvictionRunsMillis(1000 * 60 * 30)
        dataSource.setNumTestsPerEvictionRun(3)
        dataSource.setTestOnBorrow(true)
        dataSource.setTestWhileIdle(false)
        dataSource.setTestOnReturn(false)
        dataSource.setValidationQuery("SELECT 1")
        dataSource.setMaxActive(1024)
//        dataSource.setPoolSize(500)
        dataSource.setMaxActive(1024)
        dataSource.setMaxIdle(100)
        println "properties!!!! "
        dataSource.properties.each { println it }

     }
     def destroy = {
     }
} 
