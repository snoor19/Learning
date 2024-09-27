package com.spero.learn;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;

public class CalenderUtils {

	public static void main(String[] args) throws ParseException {
		Calendar lastResetDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		lastResetDate.setTime(sdf.parse("02022040"));
		System.out.println("Date::"+lastResetDate.getTime());
		System.out.println("Year::"+lastResetDate.get(Calendar.YEAR));
		System.out.println("Month::"+lastResetDate.get(Calendar.MONTH));
		System.out.println("Day::"+lastResetDate.get(Calendar.DAY_OF_MONTH));
		YearMonth yearMonthObject = YearMonth.of(lastResetDate.get(Calendar.YEAR), lastResetDate.get(Calendar.MONTH));
		System.out.println("Days in month using yearMonthObj::"+yearMonthObject.lengthOfMonth());
		System.out.println("Days in month using calender::"+lastResetDate.getActualMaximum(Calendar.DAY_OF_MONTH));
		SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
		int current = Integer.parseInt(dateFormat.format(new Timestamp(System.currentTimeMillis())));
		System.out.println("Data::"+current);
		int currentInMin = current % 100 + current /100 * 60 ;
		System.out.println("In Minutes::"+currentInMin);
		int timeToDispatch = 1612;
		int timeToDispathInMin = timeToDispatch % 100 + timeToDispatch /100 * 60 ;
		System.out.println("In conf Minutes::"+timeToDispathInMin);
	}

}
