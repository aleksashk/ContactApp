package com.aleksandrphilimonov.dao;

import com.aleksandrphilimonov.dao.domain.Contact;

import java.util.List;

public class ContactDao implements DAO<Contact, Integer> {
    @Override
    public Contact findById(Integer integer) {
        return null;
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
