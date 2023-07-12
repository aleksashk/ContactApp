package com.aleksandrphilimonov;

import com.aleksandrphilimonov.dao.ContactDao;
import com.aleksandrphilimonov.dao.domain.Contact;

import java.sql.SQLException;

import static com.aleksandrphilimonov.dao.ContactDao.getContactDao;

public class App {
    public static void main(String[] args) throws SQLException {
        ContactDao contactDao = getContactDao();
//        Contact insertionContact = new Contact();
//        insertionContact.setId(3);
//        insertionContact.setFirstName("Анжелика");
//        insertionContact.setLastName("Варум");
//        Contact contact = contactDao.insert(insertionContact);
//        System.out.println(contact);
//        System.out.println("-----------------------");
//        System.out.println(contactDao.findByAll());
//        System.out.println("-----------------------");
//        Contact updatedContact = new Contact();
//        updatedContact.setId(4);
//        updatedContact.setFirstName("Аркадий");
//        updatedContact.setLastName("Батерфляй");
//        Contact upContact = contactDao.update(updatedContact);
//        System.out.println(upContact);
//        System.out.println("-----------------------");
        System.out.println(contactDao.findByAll());
        System.out.println("-----------------------");
        System.out.println(contactDao.delete(2));
        System.out.println("-----------------------");
        System.out.println(contactDao.findByAll());
    }
}
