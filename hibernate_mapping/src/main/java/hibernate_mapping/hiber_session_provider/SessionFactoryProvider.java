package hibernate_mapping.hiber_session_provider;

import hibernate_mapping.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

public class SessionFactoryProvider {
    private static SessionFactory sessionFactory;

    private  SessionFactoryProvider() {

    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Post.class)
                    .addAnnotatedClass(School.class)
                    .addAnnotatedClass(User.class).
                            buildSessionFactory();
        }

        return sessionFactory;
    }
}
