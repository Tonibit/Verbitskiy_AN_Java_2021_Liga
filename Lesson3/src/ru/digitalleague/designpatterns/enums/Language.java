package ru.digitalleague.designpatterns.enums;

import java.util.Locale;

public enum Language {
    RUSSIAN(new Locale("ru", "RU")),
    ENGLISH(new Locale("en", "EN")),
    GERMAN(new Locale("de", "DE")),
    FRENCH(new Locale("fr", "FR")),
    SPANISH(new Locale("sp", "SP")),
    ITALIAN(new Locale("it", "IT")),
    PORTUGUESE(new Locale("por", "PO")),
    KOREAN(new Locale("ko", "KR")),
    JAPANESE(new Locale("ja", "JP"));

    Locale locale;


    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }
}
