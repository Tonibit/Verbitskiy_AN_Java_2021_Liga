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
        //formatter for month name
        SimpleDateFormat monthFormatter = new SimpleDateFormat("MMMM");
        Calendar calendar = Calendar.getInstance();
        // set first day of the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        //create new calendar as exit point in the loop
        Calendar nextMonth = Calendar.getInstance();
        nextMonth.add(Calendar.MONTH, 1);
        //print name of the month
        System.out.println(monthFormatter.format(calendar.getTime()).substring(0,1).toUpperCase() +
                monthFormatter.format(calendar.getTime()).substring(1));

        //template for output week days
        String weekDays = "пн вт ср чт пт сб вс";

        //find monday if it is not first date in month
        while (!weekDayFormatter.format(calendar.getTime()).contains("пн")) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
//            System.out.println(weekDayFormatter.format(calendar.getTime()) + " "
//                    + dateOfMonthFormatter.format(calendar.getTime()));
        }

        //until get reached next month or end of week
        // printing day of the week and date of the month and increase one day
        StringBuilder dateDays = new StringBuilder();
        while (calendar.get(Calendar.MONTH) < nextMonth.get(Calendar.MONTH) ||
                (!weekDayFormatter.format(calendar.getTime()).contains("пн"))) {
            if (Integer.parseInt(dateOfMonthFormatter.format(calendar.getTime())) < 10) {
                dateDays.append(dateOfMonthFormatter.format(calendar.getTime())).append("  ");
            } else {
                dateDays.append(dateOfMonthFormatter.format(calendar.getTime())).append(" ");
            }
            if (weekDayFormatter.format(calendar.getTime()).contains("вс")) {
                System.out.println(weekDays);
                System.out.println(dateDays.toString().trim());
                dateDays.setLength(0);
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Отобразите календарь текущего месяца в консоли
        // например:
        // пн вт ср чт пт сб вс
        // 30 31 1  2  3  4  5
        // и т.д.
    }
}
