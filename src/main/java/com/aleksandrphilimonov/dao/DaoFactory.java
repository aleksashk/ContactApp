package com.aleksandrphilimonov.dao;

import com.aleksandrphilimonov.exception.CommonServiceException;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DaoFactory {
    private static TransactionDao transactionDao;

    public static TransactionDao transactionDao() throws CommonServiceException {
        if (transactionDao == null) {
            transactionDao = new TransactionDao(getDataSource());
        }
        return transactionDao;
    }

    private static DataSource dataSource;

    public static DataSource getDataSource() throws CommonServiceException {
        if (dataSource == null) {
            HikariDataSource hikariDataSource = new HikariDataSource();
            hikariDataSource.setJdbcUrl(System.getProperty("jdbcUrl", "jdbc:postgresql://localhost:5432/postgres_db"));
            hikariDataSource.setUsername(System.getProperty("jdbcUsername", "postgres"));
            hikariDataSource.setPassword(System.getProperty("jdbcPassword", "password"));

            dataSource = hikariDataSource;

            initDataBase();
        }
        return dataSource;
    }

    private static void initDataBase() throws CommonServiceException {
        try {
            DatabaseConnection connection = new JdbcConnection(dataSource.getConnection());
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(connection);

            Liquibase liquibase = new Liquibase(
                    "liquibase.xml",
                    new ClassLoaderResourceAccessor(),
                    database
            );

            liquibase.update(new Contexts());
        } catch (SQLException | LiquibaseException e) {
            throw new CommonServiceException(e);
        }
    }

}
