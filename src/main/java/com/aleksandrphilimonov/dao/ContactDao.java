package com.aleksandrphilimonov.dao;

import com.aleksandrphilimonov.dao.domain.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static com.aleksandrphilimonov.dao.DaoFactory.getConnection;

public class ContactDao implements DAO<Contact, Integer> {

    private static ContactDao contactDao;

    private ContactDao() {
    }

    public static ContactDao getContactDao() {
        if (contactDao == null) {
            contactDao = new ContactDao();
        }
        return contactDao;
    }

    @Override
    public Contact findById(Integer id) throws SQLException {
        Contact contact = null;

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from contact where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
            }
        }

        return contact;
    }

    @Override
    public List<Contact> findByAll() {
        return null;
    }

    @Override
    public Contact insert(Contact contact) {
        return null;
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }
}
