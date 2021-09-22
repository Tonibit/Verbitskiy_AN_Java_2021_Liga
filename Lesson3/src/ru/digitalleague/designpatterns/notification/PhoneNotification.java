package ru.digitalleague.designpatterns.notification;


import ru.digitalleague.designpatterns.User;

public class PhoneNotification implements Notification {

    private String body;
    private User user;

    public PhoneNotification(String body, User user) {
        this.body = body;
        this.user = user;
    }

    @Override
    public String getText() {
        return String.format(
                "Phone #%s\n%s",
                user.getPhone(),
                body
        );
    }

    @Override
    public User getUser() {
        return user;
    }
}
