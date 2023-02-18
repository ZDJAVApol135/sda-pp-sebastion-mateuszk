package com.sda.dao;

import db.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.User;

import java.util.List;

public class UsersDAO {
    public User addUser(User user) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(user);
        transaction.commit();
        session.close();
        return user;
    }

    public boolean deleteByUsername(String username) {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, username);
        if (user == null) {
            return false;
        }
        session.remove(user);
        transaction.commit();
        session.close();
        return true;
    }

    public List<User> findAll() {
        try (Session session = HibernateUtils.openSession()) {
            List<User> users = session.createQuery("From User ", User.class).list();
            return users;
        }
    }
    public User findByUsername(String username) {
        try (Session session = HibernateUtils.openSession()) {
            return session.createQuery("from User where username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();
        }
    }
    public User update(User user) {
        Session session = HibernateUtils.openSession();
        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }
    public boolean exist(String username) {
        try(Session session = HibernateUtils.openSession()){
        String query= "Select count(1) From User u WHERE u.username=:username";
      Long userscount=  session.createQuery(query,Long.class)
                        .setParameter("username",username)
                                .uniqueResult();

        return userscount >0;
    }}
}