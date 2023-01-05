package com.spero.learn;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MonthsBtwnDates {

	public static void main(String[] args) {
		String fromDate = "2021-10-18 12:00:00";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatter = new SimpleDateFormat("MMM");
		String endDate = df.format(new Date());
		
		Date fromDDate = null;
		Date toDate = null;
		try {
			fromDDate = df.parse(fromDate);
			toDate = df.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	    Calendar beginCalendar = Calendar.getInstance();
	    Calendar finishCalendar = Calendar.getInstance();

	    beginCalendar.setTimeInMillis(fromDDate.getTime());
	    finishCalendar.setTimeInMillis(toDate.getTime());
	    
	    if (df.format(fromDDate).equals(endDate)) {
			String date = formatter.format(beginCalendar.getTime()).toUpperCase();
			String[] _monthList = { date };
			System.out.println("Data::"+_monthList[0]);
			return;
		}

	    String date;
	    while (beginCalendar.before(finishCalendar)) {
	        // add one month to date per loop
	        date = formatter.format(beginCalendar.getTime());
	        System.out.println(date.toUpperCase());
	        beginCalendar.add(Calendar.MONTH, 1);
	    }
	    
	    System.out.println("Date Format::"+"2021-10-18 00:00:00".matches("\\d{4}-\\d{2}-\\d{2}"));
		System.out.println("Long Convert::"+(float)(double)Double.valueOf("30.55"));
	    System.exit(1);
	}
	
	public static String getMonthName(int month) {
		String _month = null;
		switch (month) {
		case 0:
			_month = "JAN";
			break;
		case 1:
			_month = "FEB";
			break;
		case 2:
			_month = "MAR";
			break;
		case 3:
			_month = "APR";
			break;
		case 4:
			_month = "MAY";
			break;
		case 5:
			_month = "JUN";
			break;
		case 6:
			_month = "JUL";
			break;
		case 7:
			_month = "AUG";
			break;
		case 8:
			_month = "SEP";
			break;
		case 9:
			_month = "OCT";
			break;
		case 10:
			_month = "NOV";
			break;
		case 11:
			_month = "DEC";
			break;
		default:
			_month = "DEFAULT";
			break;
		}
		return _month;
	}

}
