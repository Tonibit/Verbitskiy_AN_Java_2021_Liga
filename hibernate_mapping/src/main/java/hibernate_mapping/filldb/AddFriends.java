package hibernate_mapping.filldb;

import hibernate_mapping.entity.User;
import hibernate_mapping.hiber_session_provider.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddFriends {

    public static void add() {

        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();

            User user1 = session.get(User.class, 1);
            User user2 = session.get(User.class, 2);
            User user3 = session.get(User.class, 3);
            User user4 = session.get(User.class, 4);

            user4.addFriend(user1);
            user4.addFriend(user2);

            user3.addFriend(user1);
            user2.addFriend(user3);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
