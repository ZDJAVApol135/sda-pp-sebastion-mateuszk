package db;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {


    private static SessionFactory sessionFactory;

    public Session openSession() {
        return getSessionFactory().openSession();
    }

    private SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class);

        return configuration.buildSessionFactory();
    }

}

