package com.aleksandrphilimonov.dao;

import com.aleksandrphilimonov.dao.domain.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    public List<Contact> findByAll() throws SQLException {
        List<Contact> list = new ArrayList<>();

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("select * from contact");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("id"));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                list.add(contact);
            }
        }

        return list;
    }

    @Override
    public Contact insert(Contact contact) throws SQLException {
        Contact insertContact;

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("insert into contact(first_name, last_name) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            insertContact = new Contact();
            insertContact.setId(rs.getInt(1));
            insertContact.setFirstName(rs.getString(2));
            insertContact.setLastName(rs.getString(3));

        }

        return insertContact;
    }

    @Override
    public Contact update(Contact contact) throws SQLException {
        Contact updatedContact;

        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("update contact set first_name = ?, last_name = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, contact.getFirstName());
            ps.setString(2, contact.getLastName());
            ps.setInt(3, contact.getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            updatedContact = new Contact();
            updatedContact.setId(rs.getInt(1));
            updatedContact.setFirstName(rs.getString(2));
            updatedContact.setLastName(rs.getString(3));

        }

        return updatedContact;
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement("delete from contact where id = ?");
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        }
        return false;
    }
}
