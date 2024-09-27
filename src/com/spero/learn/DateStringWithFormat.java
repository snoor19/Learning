package com.spero.learn;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateStringWithFormat {
	
	protected Calendar mCalendar = Calendar.getInstance();
	protected int mProcessorIntervalInMins = 60; 
	protected String mDateTimeFormat = "yyyyMMddHHmmssSSS";
	protected LocalDateTime dateTime = LocalDateTime.now();

	public static void main(String[] args) {
		DateStringWithFormat format = new DateStringWithFormat();
		System.out.println("Old Value::"+format.getFileDateTimeAsString());
		System.out.println("New Value::"+format.getFileDateTimeAsStringLatest());
	}
	
	protected String getFileDateTimeAsString() {
        String dateTime;
        long curMins;
        int adjMins, intervalMultiple;
        mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);
        curMins = mCalendar.get(Calendar.HOUR_OF_DAY) * 60 + mCalendar.get(Calendar.MINUTE);
        adjMins = (int) curMins % mProcessorIntervalInMins;
        intervalMultiple = (int) ((curMins - adjMins) / mProcessorIntervalInMins);
        System.out.println("old intervalMultiple::"+intervalMultiple);
        mCalendar.set(Calendar.MINUTE, 0);
        mCalendar.set(Calendar.HOUR_OF_DAY, 0);
        mCalendar.add(Calendar.MINUTE, intervalMultiple * mProcessorIntervalInMins);
        SimpleDateFormat sdf = new SimpleDateFormat(mDateTimeFormat);
        dateTime = sdf.format(mCalendar.getTime());
        return dateTime;
    }
	
	protected String getFileDateTimeAsStringLatest() {
        long curMins;
        int adjMins, intervalMultiple;
        LocalDate date = LocalDate.now();
    	LocalTime time = LocalTime.now();
		curMins = time.getHour()* 60 + time.getMinute();
	    adjMins =  (int) curMins % mProcessorIntervalInMins;
	    intervalMultiple = (int) ((curMins - adjMins) / mProcessorIntervalInMins);
	    System.out.println("new intervalMultiple::"+intervalMultiple);
	    time = LocalTime.of(0, 0).plusMinutes(intervalMultiple * mProcessorIntervalInMins);	
	    System.out.println("Time::"+time);
		dateTime = LocalDateTime.of(date.getYear(), date.getMonth(), date.getDayOfMonth(), time.getHour(), time.getMinute());
		System.out.println("getFileDateTimeAsString date::"+dateTime);
        return dateTime.format(DateTimeFormatter.ofPattern(mDateTimeFormat));
    }

}
