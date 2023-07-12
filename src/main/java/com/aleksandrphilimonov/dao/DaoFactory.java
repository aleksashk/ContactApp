package com.aleksandrphilimonov.dao;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DaoFactory {
    private static ServiceUserDao serviceUserDao;

    public static ServiceUserDao getServiceUserDao(){
        if(serviceUserDao == null){
            serviceUserDao = new ServiceUserDao(getDataSource());
        }
        return serviceUserDao;
    }

    private static DataSource dataSource;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            HikariDataSource hikariDataSource = new HikariDataSource();
            hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres_db");
            hikariDataSource.setUsername("postgres");
            hikariDataSource.setPassword("password");

            dataSource = hikariDataSource;
        }
        return dataSource;
    }

}
