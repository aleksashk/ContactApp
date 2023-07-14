package com.aleksandrphilimonov.dao;

import com.aleksandrphilimonov.domain.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TransactionDao implements Dao<Transaction, Long> {
    public TransactionDao(DataSource dataSource) {
    }

    @Override
    public Transaction findById(Long aLong) throws SQLException {
        return null;
    }

    @Override
    public List<Transaction> findByAll() throws SQLException {
        return null;
    }

    @Override
    public Transaction insert(Transaction transaction) throws SQLException {
        return null;
    }

    public Transaction insert(Transaction transaction, Connection connection) throws SQLException {

        PreparedStatement ps = connection.prepareStatement("INSERT INTO transaction values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setLong(1, transaction.getFromAccountId());
        ps.setLong(2, transaction.getToAccountId());
        ps.setBigDecimal(3, transaction.getSum());
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating transaction failed, no rows affected.");
        }
        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                transaction.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating transaction failed, no ID obtained.");
            }
        }
        return transaction;
    }

    @Override
    public Transaction update(Transaction transaction) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Long aLong) throws SQLException {
        return false;
    }
}
