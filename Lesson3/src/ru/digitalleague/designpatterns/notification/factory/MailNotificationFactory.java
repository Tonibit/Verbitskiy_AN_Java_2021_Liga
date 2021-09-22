package ru.digitalleague.designpatterns.notification.factory;


import ru.digitalleague.designpatterns.User;
import ru.digitalleague.designpatterns.notification.MailNotification;
import ru.digitalleague.designpatterns.notification.Notification;

public class MailNotificationFactory implements NotificationFactory {
    public Notification makeNotification(String body, User user) {
        return new MailNotification(body, user, hasAdv(user));
    }

    private boolean hasAdv(User user) {
        return user.getId() % 2 == 0;
    }
}
