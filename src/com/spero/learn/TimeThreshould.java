package com.spero.learn;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class TimeThreshould {

	public static void main(String[] args) {
		String TRA_DATE_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat(TRA_DATE_FORMATTER);
		Timestamp thresh = null;
		try {
			thresh = new Timestamp(dateFormat.parse("2022-09-14T10:56:30").getTime());
			Timestamp expiry = new Timestamp(sdf.parse("2023-02-05").getTime());
			
			/*
			 * System.out.println("Value::"+thresh.after(expiry));
			 * System.out.println("expiry::"+expiry);
			 */
			long diffInMillies = Math.abs(thresh.getTime() - new Date().getTime());
			long daysBetween = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			System.out.println("HoursBetween::"+daysBetween);
			Period period = Period.between(thresh.toLocalDateTime().toLocalDate(), LocalDate.now());
			Calendar start = Calendar.getInstance();
			start.setTime(thresh);
			start.add(Calendar.MONTH, 3);
			Calendar end = new GregorianCalendar();
			end.setTime(new Date());
			int yearDiff = end.get(Calendar.YEAR) 
	                - start.get(Calendar.YEAR);
			System.out.println("Only Year::"+yearDiff);
			int monthDiff = end.get(Calendar.MONTH) 
	                - start.get(Calendar.MONTH);
			System.out.println("Only month::"+monthDiff);
			System.out.println("Months::"+(yearDiff*12+monthDiff));
			System.out.println("start::"+start.getTime());
			System.out.println("After::"+new Date().after(start.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TimeStamp::"+new Timestamp(System.currentTimeMillis()));
		System.out.println("Threshold ::"+thresh);
		DateFormat formatter= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss Z");
		  formatter.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
		  System.out.println(formatter.format(new Date()));

		  formatter.setTimeZone(TimeZone.getTimeZone("GMT-6"));
		  System.out.println(formatter.format(new Date()));
	}

}
