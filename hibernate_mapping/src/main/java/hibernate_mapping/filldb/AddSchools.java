package hibernate_mapping.filldb;

import hibernate_mapping.entity.School;
import hibernate_mapping.hiber_session_provider.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddSchools {

    public static void add() {

        School school1 = new School("School of Alias", "Col. Roma, Del.");
        School school2 = new School("International Rock School", "Awesome street");
        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();

            session.persist(school1);
            session.persist(school2);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
