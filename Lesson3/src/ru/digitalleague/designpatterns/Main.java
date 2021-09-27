package ru.digitalleague.designpatterns;


import ru.digitalleague.designpatterns.enums.Language;
import ru.digitalleague.designpatterns.notification.Notification;
import ru.digitalleague.designpatterns.notification.decorator.NotificationDecorator;
import ru.digitalleague.designpatterns.notification.factory.MailNotificationFactory;
import ru.digitalleague.designpatterns.notification.factory.NotificationFactory;
import ru.digitalleague.designpatterns.notification.factory.PhoneNotificationFactory;

public class Main {

    public static void main(String[] args) {
        User user = new User(2L, "Денис", "denis@gmail.com", "+79522668105");
        User user1 = new User(21L, "Leo", "LeoMeihn@yahoo.com", "+34586321156", Language.GERMAN);

        NotificationFactory factory = new MailNotificationFactory();
        System.out.println("Example with MailNotification(language didn't set)");
        sendNotification(new NotificationDecorator(factory.makeNotification(user)));

        user.setLanguage(Language.JAPANESE);
        System.out.println("\n\nExample with MailNotification(language is set to " + user.getLanguage() + ")");
        sendNotification(new NotificationDecorator(factory.makeNotification(user)));

        factory = new PhoneNotificationFactory();
        System.out.println("\n\nExample with PhoneNotification(language: " + user1.getLanguage() + ")");
        sendNotification(new NotificationDecorator(factory.makeNotification(user1)));

    }

    private static void sendNotification(Notification notification) {
        System.out.println(notification.getText());
    }
}
