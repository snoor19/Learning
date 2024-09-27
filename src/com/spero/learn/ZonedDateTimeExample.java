package com.spero.learn;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeExample {

//    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
	private static final String DATE_FORMAT = "yyyyMMddHHmmss";

    public static void main(String[] args) {

//        String dateInString = "24-1-2023 02:34:55 PM";
        String dateInString = "20221202135046";
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");
        System.out.println("TimeZone : " + singaporeZoneId);

        //LocalDateTime + ZoneId = ZonedDateTime
        ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
        System.out.println("Date (Singapore) : " + asiaZonedDateTime);

        ZoneId newYokZoneId = ZoneId.of("America/New_York");
        System.out.println("TimeZone : " + newYokZoneId);

        ZonedDateTime nyDateTime = asiaZonedDateTime.withZoneSameInstant(newYokZoneId);
        System.out.println("Date (New York) : " + nyDateTime);
        
        ZoneId indiaZoneId = ZoneId.of("Asia/Calcutta");
        System.out.println("TimeZone : " + indiaZoneId);

        ZonedDateTime inDateTime = asiaZonedDateTime.withZoneSameInstant(indiaZoneId);
        System.out.println("Date (India) : " + inDateTime);
        System.out.println("In Timestamp::"+Timestamp.valueOf(inDateTime.toLocalDateTime()));
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        System.out.println("\n---DateTimeFormatter---");
        System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
        System.out.println("Date (New York) : " + format.format(nyDateTime));

    }

}
