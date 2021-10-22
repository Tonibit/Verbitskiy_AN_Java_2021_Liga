package hibernate_mapping.filldb;

import hibernate_mapping.entity.Post;
import hibernate_mapping.entity.User;
import hibernate_mapping.hiber_session_provider.SessionFactoryProvider;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AddPostsAndAttachToUsers {

    public static void addPostsAndAttach() {
        Post post1 = new Post("Hello mate!");
        Post post2 = new Post("So cooL!");
        Post post3 = new Post("What do you do?");
        Post post4 = new Post("Exception detected./");
        Post post5 = new Post("Interesting question");
        Post post6 = new Post("try not to catch");
        Transaction transaction = null;

        try (Session session = SessionFactoryProvider.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();

            User user1  = session.get(User.class, 1);
            User user2  = session.get(User.class, 2);
            User user3  = session.get(User.class, 3);

            user1.addPost(post1);
            user1.addPost(post5);
            user2.addPost(post3);
            user3.addPost(post4);
            user2.addPost(post6);
            user1.addPost(post2);

            session.merge(user1);
            session.merge(user3);

            session.persist(post3);
            session.persist(post6);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
