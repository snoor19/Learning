package com.spero.learn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeStampTesting {

	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static void main(String[] args) throws ParseException {

		Calendar cal = Calendar.getInstance();
		String date = "2022-11-15 22:45:00";
		SimpleDateFormat sdfO = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cal.setTime(sdfO.parse(date));
//		cal.setTime(new Date(1668598447892l));
		System.out.println("DB time::" + cal.getTime());
		TimeZone arizoneTz = TimeZone.getTimeZone("GMT-7:00");
		TimeZone alaskaTz = TimeZone.getTimeZone("GMT-9:00");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(arizoneTz);
		System.out.println("Arizone setting Current time::"+sdf.format(cal.getTime()));
		sdf.setTimeZone(alaskaTz);
		System.out.println("TOMCAT setting Current time::"+sdf.format(cal.getTime()));
		System.out.println("===========================================");
		testTime();
	}//1668598447892
	
	public static void testTime() {
		String dateInString = "2015-01-22 10:15:55";
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        ZoneId singaporeZoneId = ZoneId.of("US/Arizona");
        System.out.println("TimeZone : " + singaporeZoneId);

        //LocalDateTime + ZoneId = ZonedDateTime
        ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
        System.out.println("Date (Singapore) : " + asiaZonedDateTime);

        ZoneId newYokZoneId = ZoneId.of("US/Alaska");
        System.out.println("TimeZone : " + newYokZoneId);

        ZonedDateTime nyDateTime = asiaZonedDateTime.withZoneSameInstant(newYokZoneId);
        System.out.println("Date (New York) : " + nyDateTime);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        System.out.println("\n---DateTimeFormatter---");
        System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
        System.out.println("Date (New York) : " + format.format(nyDateTime));
	}

}
