package ru.philosophyit.internship.javabase.time;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Main {
    public static void main(String... args) {
        System.out.println(DateTimeFormatter.ISO_INSTANT.format(Calendar.getInstance().toInstant()));

        //formatter for showing day of the week : пн, вт, ср, etc.
        SimpleDateFormat weekDayFormatter = new SimpleDateFormat("E");
        //formatter for showing date of the month : 1, 2, 3 etc.
        SimpleDateFormat dateOfMonthFormatter = new SimpleDateFormat("d");
        Calendar calendar = Calendar.getInstance();
        // set first day of the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        //create new calendar as exit point in the loop
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.add(Calendar.MONTH, 1);

        //until get reached next month printing day of the week and date of the month and increase one day
        while (calendar.get(Calendar.MONTH) < nextMonth.get(Calendar.MONTH)) {
            System.out.println(weekDayFormatter.format(calendar.getTime()) + " "
                    + dateOfMonthFormatter.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Отобразите календарь текущего месяца в консоли
        // например:
        // пн вт ср чт пт сб вс
        // 30 31 1  2  3  4  5
        // и т.д.
    }
}
