package ru.digitalleague.designpatterns.notification;


import ru.digitalleague.designpatterns.TemplateMessage;
import ru.digitalleague.designpatterns.User;

public class MailNotification implements Notification {

    private User user;
    private boolean hasAdvertisement;

    public MailNotification(User user, boolean hasAdvertisement) {
        this.user = user;
        this.hasAdvertisement = hasAdvertisement;
    }

    @Override
    public String getText() {
        return String.format(
                TemplateMessage.getMailTemplate(),
                user.getEmail(),
                user.getName(),
                hasAdvertisement ? TemplateMessage.getAdvertisement() : ""
        );
    }

    @Override
    public User getUser() {
        return user;
    }
}
