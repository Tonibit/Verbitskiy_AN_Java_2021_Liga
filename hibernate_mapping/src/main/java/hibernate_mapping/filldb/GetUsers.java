package hibernate_mapping.filldb;

import hibernate_mapping.entity.School;
import hibernate_mapping.entity.User;
import hibernate_mapping.hiber_session_provider.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GetUsers {

    public static void get() {
        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();

            List<User> userList = session.createQuery("From User", User.class).getResultList();

            userList.forEach(System.out::println);


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
