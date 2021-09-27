package ru.digitalleague.designpatterns.notification.decorator;


import ru.digitalleague.designpatterns.TemplateMessage;
import ru.digitalleague.designpatterns.User;
import ru.digitalleague.designpatterns.notification.Notification;

import java.util.ResourceBundle;

public class NotificationDecorator implements Notification {

    private Notification wrapper;

    public NotificationDecorator(Notification wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public String getText() {
        ResourceBundle rb = ResourceBundle.getBundle("resource/translatetext",
                getUser().getLanguage().getLocale());

        // Меняем язык в шаблоне
        TemplateMessage.setLanguage(rb);

        return wrapper.getText();
    }

    @Override
    public User getUser() {
        return wrapper.getUser();
    }
}
