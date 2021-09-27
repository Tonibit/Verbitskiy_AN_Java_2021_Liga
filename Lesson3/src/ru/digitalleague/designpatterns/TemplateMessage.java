package ru.digitalleague.designpatterns;


import ru.digitalleague.designpatterns.enums.Template;

import java.util.ResourceBundle;

public class TemplateMessage {

    private static String mailTemplate = Template.MAIL.getMessage();
    private static String phoneTemplate = Template.PHONE.getMessage();
    private static String advertisement = "Buy our products!";
    private static ResourceBundle resourceBundle;

    public static String getMailTemplate() {
        return mailTemplate;
    }

    public static String getPhoneTemplate() {
        return phoneTemplate;
    }

    public static String getAdvertisement() {
        return advertisement;
    }

    public static void setLanguage(ResourceBundle rb) {
        resourceBundle = rb;
        setMailTemplate();
        setPhoneTemplate();
    }

    private static void setMailTemplate() {
        mailTemplate = resourceBundle.getString("Address") + ": %s\n"
                + resourceBundle.getString("Dear") + " %s,\n" + resourceBundle.getString("Welcome")
                + "\n\n%s\n\n" + resourceBundle.getString("Greetings");
        advertisement = resourceBundle.getString("Advertisement");

    }

    private static void setPhoneTemplate() {
        phoneTemplate = resourceBundle.getString("Phone") + " #%s\n"
                + resourceBundle.getString("Welcome");
    }


}
