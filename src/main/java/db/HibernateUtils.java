package db;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    private static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(model.User.class);
        return configuration.configure().buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }
}
