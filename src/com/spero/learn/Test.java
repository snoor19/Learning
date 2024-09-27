package com.spero.learn;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class Test {

	static SimpleDateFormat processSDF = new SimpleDateFormat("yyyyMMddHHmm");
	long val = 0l;

	public static void main(String args[]) throws ParseException {
		Test1 test1 = new Test1();
		String time = "060820";
		String appendFormat = "yyyyMM";
		SimpleDateFormat dateYYYYMMFormat = new SimpleDateFormat(appendFormat);
//		String yearMonth = dateYYYYMMFormat.format(new Timestamp(System.currentTimeMillis()));
		String yearMonth = "202309";
		System.out.println("yearMonth::" + yearMonth);
		String nextProcessTime = yearMonth + time;
		System.out.println("nextProcessTime::" + nextProcessTime);
		System.out.println("processSDF.parse(nextProcessTime).getTime()" + processSDF.parse(nextProcessTime));
		Timestamp nextProcess = new Timestamp(processSDF.parse(nextProcessTime).getTime());
		System.out.println("nextProcess::" + nextProcess);
		
		int addOn = 44640; // month-43800
//		int addOn = 1440;  //Day
// 		int addOn = 60;   //hour
// 		int addOn = 20; //custom minutes
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(nextProcess);
		if( addOn == 44640)
			cal.add(Calendar.MONTH, 1);
		else
			cal.add(Calendar.MINUTE, addOn);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Timestamp(System.currentTimeMillis()));
		System.out.println("cal1:-" + cal.getTime() + ", Cal2:-" + cal2.getTime());

		while (!cal.after(cal2)) {
			System.out.println("inside");
			long diff = System.currentTimeMillis() - cal.getTimeInMillis();
			long diffHours = diff / (60 * 60 * 1000);
			System.out.println("diffHours::" + diffHours);
			cal.add(Calendar.HOUR, (int) (diffHours));
			if( addOn == 44640)
				cal.add(Calendar.MONTH, 1);
			else
				cal.add(Calendar.MINUTE, addOn);
			System.out.println("cal1:-" + cal.getTime());
		}
		 
		System.out.println("Final Date::"+new Timestamp(cal.getTimeInMillis()));
		
		long curMins;
        int adjMins;
        int intervalMultiple;
		LocalDate date = LocalDate.now();
	    LocalTime time1 = LocalTime.now();
	    curMins = time1.getHour()* 60l + time1.getMinute();
	    adjMins =  (int) curMins % 20;
	    intervalMultiple = (int) (curMins - adjMins);
	    System.out.println("intervalMultiple::"+intervalMultiple);
	    time1 = LocalTime.of(0, 0).plusMinutes(intervalMultiple);		
	    LocalDateTime dateTimeZone = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), time1.getHour(), time1.getMinute());
	    System.out.println("getFileDateTimeAsString date::{}"+dateTimeZone);
	}
}
