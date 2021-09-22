package ru.digitalleague.designpatterns.notification.decorator;


import ru.digitalleague.designpatterns.User;
import ru.digitalleague.designpatterns.enums.Template;
import ru.digitalleague.designpatterns.notification.MailNotification;
import ru.digitalleague.designpatterns.notification.Notification;

import java.util.Locale;
import java.util.ResourceBundle;

public class NotificationDecorator implements Notification {

    private Notification wrapper;

    public NotificationDecorator(Notification wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public String getText() {
        ResourceBundle rb;
        //Если не установили язык у пользователя, то будет вызван дефолтный (англ)
        if (getUser().getLanguage() == null) {
            rb = ResourceBundle.getBundle("resource/translatetext",
                    new Locale(""));
        } else {
            rb = ResourceBundle.getBundle("resource/translatetext",
                    getUser().getLanguage().getLocale());
        }

        //Проверяем какой тип оповещения выбран и в соответствии с этим подставляем нужный шаблон уведомления.
        if (wrapper instanceof MailNotification) {
            return String.format(Template.MAIL.getMessage(),
                    rb.getString("Address"),
                    getUser().getEmail(),
                    rb.getString("Dear"),
                    getUser().getName(),
                    rb.getString("Welcome"),
                    getUser().getId() % 2 == 0 ? "\n\n" + rb.getString("Advertisement") + "\n" : "",
                    rb.getString("Greetings"));

        } else {
            return String.format(Template.PHONE.getMessage(),
                    rb.getString("Phone"),
                    getUser().getPhone(),
                    rb.getString("Welcome"));
        }
    }

    @Override
    public User getUser() {
        return wrapper.getUser();
    }
}
