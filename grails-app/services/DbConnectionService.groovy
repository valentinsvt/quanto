/*
import Connection

class DbConnectionService {

    boolean transactional = true

    Connection cn = null
    protected dataSourceProperties

    def dataSource

    public init(){
       

    }

    */
/** * Devuelve la conexión a la base de datos *//*

    public  Connection getConnection(){
        def db=new db()
        println "url:${dataSourceProperties.url}  user: ${dataSourceProperties.username} pass: ${dataSourceProperties.password}"
        cn = ConnectionFactory.getConnection("Fire")
        cn.connect( dataSourceProperties.url, dataSourceProperties.driverClassName, dataSourceProperties.username,dataSourceProperties.password)
        return cn
    }


}
*/

import groovy.sql.Sql

class DbConnectionService {
    boolean transactional = false
    def dataSource
    public init(){
    }

    /**
     * Devuelve la conexión a la base de datos
     */

    def getConnection(){

        Sql sql = new Sql(dataSource)
        return sql
    }

}
