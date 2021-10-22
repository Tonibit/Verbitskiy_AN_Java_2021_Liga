package hibernate_mapping.filldb;

import hibernate_mapping.entity.School;
import hibernate_mapping.hiber_session_provider.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteSchool {

    public static void deleteSchool() {
        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();

            School school = session.get(School.class, 1);
            session.delete(school);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
