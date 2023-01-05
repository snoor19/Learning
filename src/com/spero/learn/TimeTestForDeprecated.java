package com.spero.learn;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeTestForDeprecated {

	public static void main(String[] args) throws ParseException {

		Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse("2021-01-14 12:29");
		System.out.println("Start Date::"+start);
//		System.out.println("Hours ::"+start.getHours());
//        System.out.println("Minute ::"+start.getMinutes());
       
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(start);
        
        System.out.println("Date with new ::"+startTime.getTime());
        System.out.println("Day ::"+startTime.get(Calendar.DAY_OF_MONTH));
        System.out.println("Hours ::"+startTime.get(Calendar.HOUR));
        System.out.println("Minute ::"+startTime.get(Calendar.MINUTE));
        
        Calendar startDate = Calendar.getInstance();
        startDate.set(Calendar.HOUR_OF_DAY, startTime.get(Calendar.HOUR));
        startDate.set(Calendar.MINUTE,startTime.get(Calendar.MINUTE));
        
        System.out.println("Setting Date::"+startDate);
        System.out.println("DAte::"+startDate.getTime());
        
        Calendar currTime = Calendar.getInstance();
        
        System.out.println("Current Year::"+currTime.get(Calendar.YEAR));
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.YEAR, 0); 
        System.out.println("Data::"+cal.getTime());
        
	}

}
