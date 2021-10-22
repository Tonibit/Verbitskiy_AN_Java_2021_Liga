package hibernate_mapping.filldb;

import hibernate_mapping.entity.School;
import hibernate_mapping.entity.User;
import hibernate_mapping.hiber_session_provider.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddUsers {

    public static void addUser() {

        User user1 = new User("Paul", "Fikowski", 19, 'M');
        User user2 = new User("Maya", "Thorsdottir", 29, 'F');
        User user3 = new User("Melisa", "Velington", 17, 'F');
        User user4 = new User("Roger", "That", 24, 'M');

        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();

            School school1 = session.get(School.class, 1);
            School school2 = session.get(School.class, 2);

            school1.addUser(user3);
            school1.addUser(user1);
            school1.addUser(user4);
            school2.addUser(user2);

            session.persist(user1);
            session.persist(user2);
            session.persist(user3);
            session.persist(user4);


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
