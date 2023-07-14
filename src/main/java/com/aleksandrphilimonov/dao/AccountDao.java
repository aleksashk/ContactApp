package com.aleksandrphilimonov.dao;

import com.aleksandrphilimonov.domain.Account;
import com.aleksandrphilimonov.exception.CommonServiceException;
import com.sun.xml.internal.ws.util.xml.CDATA;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AccountDao implements Dao<Account, Integer> {

    private static DataSource dataSource;

    @Override
    public Account findById(Integer integer) throws SQLException {
        return null;
    }

    public Account findById(Long id, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public List<Account> findByAll() throws SQLException {
        return null;
    }

    @Override
    public Account insert(Account account) throws SQLException, CommonServiceException {

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO account(name, balance) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getName());
            ps.setBigDecimal(2, account.getBalance());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating transaction failed, no rows affected.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    account.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating transaction failed, no ID obtained.");
                }
            }
            return account;
        } catch (SQLException e) {
            throw new CommonServiceException(e);
        }
    }

    @Override
    public Account update(Account account) throws SQLException {
        return null;
    }

    public Account update(Account account, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("update account set name = ?, balance=? where id = ?");
        ps.setString(1, account.getName());
        ps.setBigDecimal(2, account.getBalance());
        ps.setLong(3, account.getId());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Updated account failed, no rows affected.");
        }
        return account;
    }

    @Override
    public boolean delete(Integer integer) throws SQLException {
        return false;
    }
}
