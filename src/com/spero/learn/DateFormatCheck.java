package com.spero.learn;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatCheck {

	public static void main(String[] args) {
		try {
			String trans_time = "2022-05-24T23:04:06Z";
			SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			System.out.println("Form Date::"+form.format(new Date()));
			System.out.println("trans_Time::"+form.parse(trans_time));
			/*
			 * SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd"); String
			 * reformattedDate = myFormat.format(form.parse(trans_time));
			 * System.out.println("Reformad Date::"+reformattedDate);
			 */
			 long l = 60*1000*60*24*30;
			 Calendar cal = Calendar.getInstance();
		        cal.setTime(new Date());// w ww.  j ava  2  s  .co m
		        cal.add(Calendar.YEAR, 1000); //minus number would decrement the days
	           System.out.println("Time::"+new Timestamp(cal.getTime().getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
