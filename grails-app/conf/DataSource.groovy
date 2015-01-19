dataSource {
    pooled = true
    driverClassName = "org.firebirdsql.jdbc.FBDriver"
    dialect = "org.hibernate.dialect.InterbaseDialect"

}

hibernate {
    cache.use_second_level_cache=true
    cache.use_query_cache=true
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
}
// environment specific settings
environments {

    development {
        dataSource {
            dbCreate = "update"

            url = "jdbc:firebirdsql:10.0.0.1/3050:/db/encu.gdb"

            username = "sysdba"
            password = "admin"
        }

    }
    test {
        dataSource {
            dbCreate = "update"

            url = "jdbc:firebirdsql:10.0.0.1/3050:/db/encu.gdb"

            username = "sysdba"
            password = "admin"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
//            url = "jdbc:firebirdsql:172.20.4.6/3050:/db/encu.gdb"   /* UPEC*/
            url = "jdbc:firebirdsql:127.0.0.1/3050:/db/encu.gdb"   /* UNACH*/
            username = "sysdba"
            password = "admin"
        }
    }
}
