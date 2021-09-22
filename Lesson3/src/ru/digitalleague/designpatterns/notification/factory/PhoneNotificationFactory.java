package ru.digitalleague.designpatterns.notification.factory;


import ru.digitalleague.designpatterns.User;
import ru.digitalleague.designpatterns.notification.Notification;
import ru.digitalleague.designpatterns.notification.PhoneNotification;

public class PhoneNotificationFactory implements NotificationFactory {
    public Notification makeNotification(String body, User user) {
        return new PhoneNotification(body, user);
    }
}
