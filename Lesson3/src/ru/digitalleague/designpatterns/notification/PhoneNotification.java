package ru.digitalleague.designpatterns.notification;


import ru.digitalleague.designpatterns.TemplateMessage;
import ru.digitalleague.designpatterns.User;

public class PhoneNotification implements Notification {

    private User user;

    public PhoneNotification(User user) {
        this.user = user;
    }

    @Override
    public String getText() {
        return String.format(
                TemplateMessage.getPhoneTemplate(),
                user.getPhone()
        );
    }

    @Override
    public User getUser() {
        return user;
    }
}
