//package finix

//import groovy.sql.Sql
import org.codehaus.groovy.grails.commons.ApplicationHolder
import groovy.sql.Sql
import org.codehaus.groovy.grails.commons.ConfigurationHolder
abstract class Connection {
    /** * Instancia de la connexión a la BD */
    def db
    def url
    def driver
    def user
    def password

    boolean connected = false
    
    /** * Función para setear el driver y el url */
    abstract void setServer(strServer,strDB)
           
    protected void setServer(strUrl, strDriver, strUsername, strPassword){
        driver = strDriver 
        url = strUrl
        user = strUsername
        password = strPassword
    }
    
    boolean connect(strUrl, strDriver, strUser, strPassword) {
        try {
            if(connected) {
                disconnect();
            }
            setServer(strUrl, strDriver, strUser, strPassword)
//            db = Sql.newInstance(url, user, password, driver)
            def ds =ApplicationHolder.application.mainContext.dbConnectionService.dataSource
//            println "conneccion pooleada"
            db=  new Sql(ds)
            connected = true
        } catch(Exception ex) {
            db = null
            connected = false
            println "Error de coneccion "+ex.message
        }
        return connected
    }

    String execSql(String strSql) {
        if(connected) {
            String bresult = "true"
            try {
                db.execute strSql
            } catch(Exception ex) {
                bresult= "ERROR: execSql(String) cannot execute ''" + strSql + "'   "
                bresult+= ex.message
                println "error exec "+bresult+"  --->> "+ex.message
            }
            return bresult
        }
        else
            return "Error"
    }

    def insert(String sql){
        if(connected) {
            def bresult = "true"
            try {
                db.execute strSql
            } catch(Exception ex) {
                bresult= "ERROR: execSql(String) cannot execute ''" + strSql + "'   "
                bresult+= ex.message
                println "error exec "+bresult+"  --->> "+ex.message
            }
            return bresult
        }
        else
            return "Error"
    }
    /** * Cierra la conexión con la BD */
    void disconnect() {
        if(db != null) {
            db.close()
        }        
        db = null
    }

}

// === MS-SqlServer connection ===
class SQLSERVERConnection extends Connection {
    void setServer(strServer,strDB) {
        driver = 'com.microsoft.jdbc.sqlserver.SQLServerDriver'
        url = "jdbc:microsoft:sqlserver://$strServer:1433;databaseName=$strDB"
    }
}

// === MySQL connection ===
class MySqlConnection extends Connection {
    void setServer(strServer,strDB) {
        driver = 'com.mysql.jdbc.Driver'
        url = "jdbc:mysql://$strServer:3306/$strDB"
    }
}

//=== PostgreSQL connection ===
class PgSqlConnection extends Connection {
    void setServer(strServer,strDB) {
    	driver = 'org.postgresql.Driver'
        url = "jdbc:postgresql://$strServer:5432/$strDB"        
    }
}

//== Oracle connection ==
class OraConnection extends Connection {
  void setServer(strServer,strDB) {
    driver = 'oracle.jdbc.driver.OracleDriver'
    url = "jdbc:oracle:thin:@$strServer:1521:$strDB"
  }
}

//== FireBird connection ==
class FireConnection extends Connection {
  void setServer(strServer,strDB) {
    driver = 'org.firebirdsql.jdbc.FBDriver'
    url = "jdbc:firebirdsql:$strServer/3050:$strDB"
  }
}


// === Factory Class ===
class ConnectionFactory {
  static Connection getConnection(dbType) {
    if(dbType == 'MySQL') 
      return new MySqlConnection()
    else if(dbType == 'MS-SqlServer') 
      return new SQLSERVERConnection()
    else if(dbType == 'PgSql') 
      return new PgSqlConnection()
    else if(dbType == 'Fire')
      return new FireConnection()
    else {
      println "not a valid connection..."
      return null ;
    }            
  }
}
