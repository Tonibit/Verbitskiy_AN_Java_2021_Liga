package hibernate_mapping;


import hibernate_mapping.filldb.*;
import hibernate_mapping.hiber_session_provider.SessionFactoryProvider;
import org.flywaydb.core.Flyway;

public class Main {

    public static void main(String[] args) {

        //Выполняем миграцию
        Flyway flyway = Flyway.configure()
                .dataSource("jdbc:postgresql://localhost:5432/social_db", "user", "password")
                .locations("db/migration")
                .load();
        flyway.migrate();


        try {
            //добавляем данные в базу
            AddSchools.add();
            AddUsers.addUser();
            AddPostsAndAttachToUsers.addPostsAndAttach();
            AddFriends.add();

            //смотрим что добавилось
            GetSchools.get();
            GetUsers.get();
            GetFriends.get();
            GetPosts.get();

            //удаляем некоторые данные из базы
            DeleteSchool.deleteSchool();
            DeleteUser.deleteUser();

            //смотрим, что получилось
            GetSchools.get();
            GetUsers.get();
            GetFriends.get();
            GetPosts.get();

        } finally {
            SessionFactoryProvider.getSessionFactory().close();
        }
    }
}
