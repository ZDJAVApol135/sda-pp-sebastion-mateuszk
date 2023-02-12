package com.sda.dao;

import db.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.User;

public class UsersDAO {
    public void addUser(User user) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    public boolean deleteByUsername(String username) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, username);
        if (user == null) {
            return false;
        }
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }
}