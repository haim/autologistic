package com.estafeta.test.ui.helper;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateAndTime {

  public static String getTime(int n) {

    DateTime dateTime = new DateTime();
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("hh:mm");

    String srt = dateTimeFormatter.print(dateTime.plusHours(n));

    return srt;
  }

  public static String getDatePlusDays(int d) {
    DateTime dateTime = new DateTime();
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy/m/d");
    DateTimeFormatter dateTimeFormatter2 = DateTimeFormat.forPattern("M/d/yyyy");

    String str = dateTimeFormatter2.print(dateTime.plusDays(d));
    //    String str2 = dateTime.toString(DateTimeFormat.fullDate());

    return str;
  }

  public static String getDateMinusDays(int d) {
    DateTime dateTime = new DateTime();
    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy/m/d");
    DateTimeFormatter dateTimeFormatter2 = DateTimeFormat.forPattern("M/d/yyyy");

    String str = dateTimeFormatter2.print(dateTime.minusDays(d));
    //    String str2 = dateTime.toString(DateTimeFormat.fullDate());

    return str;
  }

  public static void main(String[] args) {

    System.out.println(getTime(2));
    System.out.println(getTime(0));

    System.out.println(getDatePlusDays(2));
    System.out.println(getDatePlusDays(0));
  }
}
