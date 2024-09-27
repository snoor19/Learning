package com.spero.learn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.bouncycastle.asn1.dvcs.Data;

public class TimeZoneTest {
	
	public static void main(String[] args) {
		int mProcessorIntervalInMins = 60;
		LocalDateTime dateTime = null;
		long curMins;
        int adjMins, intervalMultiple;
        LocalDate date = LocalDate.now();
    	LocalTime time = LocalTime.now();
		curMins = time.getHour()* 60 + time.getMinute();
	    adjMins =  (int) curMins % mProcessorIntervalInMins;
	    intervalMultiple = (int) ((curMins - adjMins) / mProcessorIntervalInMins);
	    time = LocalTime.of(0, 0).plusMinutes(intervalMultiple * mProcessorIntervalInMins);		
		dateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), time.getHour(), 0);
		System.out.println("dateTime::"+dateTime);
        System.out.println("Date::"+dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        
        String x = System.currentTimeMillis()+"";

		DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HH");

		long milliSeconds= Long.parseLong(x);
		System.out.println("milliSeconds::"+milliSeconds);

		Date calender = new Date(milliSeconds);
		System.out.println("EndTime::"+formatter.format(calender)); 
	}

}
