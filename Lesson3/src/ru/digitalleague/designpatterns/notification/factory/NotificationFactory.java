package ru.digitalleague.designpatterns.notification.factory;


import ru.digitalleague.designpatterns.User;
import ru.digitalleague.designpatterns.notification.Notification;

public interface NotificationFactory {
    Notification makeNotification(String text, User user);
}
