package hibernate_mapping.filldb;

import hibernate_mapping.entity.User;
import hibernate_mapping.hiber_session_provider.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeleteUser {

    public static void deleteUser() {
        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();

            User user = session.get(User.class, 3);
            session.delete(user);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
