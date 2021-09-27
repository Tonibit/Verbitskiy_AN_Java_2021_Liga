package ru.digitalleague.designpatterns.enums;

public enum Template {
    MAIL("Address: %s\nDear %s,\nGood day!\n\n%s\n\nSincerely, Support team!"),
    PHONE("Phone #%s\nGood day!");

    String message;

    public String getMessage() {
        return message;
    }

    Template(String message) {
        this.message = message;
    }
}
