package ru.digitalleague.designpatterns.enums;

public enum Template {
    MAIL("%s: %s\n%s %s,\n%s%s\n%s"),
    PHONE("%s #%s\n%s");

    String message;

    public String getMessage() {
        return message;
    }

    Template(String message) {
        this.message = message;
    }
}
