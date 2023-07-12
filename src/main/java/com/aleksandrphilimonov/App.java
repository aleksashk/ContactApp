package com.aleksandrphilimonov;

import com.aleksandrphilimonov.dao.ContactDao;
import com.aleksandrphilimonov.dao.domain.Contact;

import java.sql.SQLException;

import static com.aleksandrphilimonov.dao.ContactDao.getContactDao;

public class App {
    public static void main(String[] args) throws SQLException {
        ContactDao contactDao = getContactDao();
        Contact contact = contactDao.findById(1);
        System.out.println(contact);
    }
}
