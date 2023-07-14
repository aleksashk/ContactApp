package com.aleksandrphilimonov.service;

import com.aleksandrphilimonov.dao.AccountDao;
import com.aleksandrphilimonov.dao.TransactionDao;
import com.aleksandrphilimonov.domain.Account;
import com.aleksandrphilimonov.domain.Transaction;
import com.aleksandrphilimonov.exception.CommonServiceException;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateTransactionService {
    private final TransactionDao transactionDao;
    private final AccountDao accountDao;
    private final DataSource dataSource;

    public CreateTransactionService(TransactionDao transactionDao, AccountDao accountDao, DataSource dataSource) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
        this.dataSource = dataSource;
    }

    public void createTransaction(Long fromAccountId, Long toAccountId, BigDecimal sum) throws CommonServiceException {
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            Account fromAccount = accountDao.findById(fromAccountId, connection);
            if (fromAccount == null) {
                throw new CommonServiceException("Can't find account by id: " + fromAccountId);
            }
            Account toAccount = accountDao.findById(toAccountId, connection);
            if (toAccount == null) {
                throw new CommonServiceException("Can't find account by id: " + toAccount);
            }

            BigDecimal fromAccountBalance = fromAccount.getBalance();
            if (fromAccountBalance.compareTo(sum) < 0) {
                throw new CommonServiceException("Insufficient funds in the account: " + fromAccountId);
            }
            fromAccount.setBalance(fromAccountBalance.add(sum.negate()));
            accountDao.update(fromAccount, connection);

            BigDecimal toAccountBalance = toAccount.getBalance();
            toAccount.setBalance(toAccountBalance.add(sum));
            accountDao.update(toAccount, connection);

            Transaction transaction = new Transaction();
            transaction.setFromAccountId(fromAccountId);
            transaction.setToAccountId(toAccountId);
            transaction.setSum(sum);
            transactionDao.insert(transaction, connection);

            connection.commit();
        } catch (SQLException | CommonServiceException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ignored) {
                }
            }
            throw new CommonServiceException(e);
        } finally {
            {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException ignored) {
                    }
                }
            }
        }
    }
}
