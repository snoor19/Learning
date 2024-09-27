package com.spero.learn;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.onmobile.prism.core.Subscription;

public class DateFormatter {

	/**
	 * Utility function to convert java Date to TimeZone format
	 * @param date
	 * @param format
	 * @param timeZone
	 * @return
	 */
	public static String formatDateToString(Date date, String format,
			String timeZone) {
		// null check
		if (date == null) return null;
		// create SimpleDateFormat object with input format
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// default system timezone if passed null or empty
		if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
			timeZone = Calendar.getInstance().getTimeZone().getID();
		}
		// set timezone to SimpleDateFormat
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		// return Date in required format with timezone as String
		return sdf.format(date);
	}

	public static void main(String[] args) {
		//Test formatDateToString method
		Date date = new Date();
		System.out.println("Default Date:"+date.toString());
		System.out.println("System Date: "+formatDateToString(date, "dd MMM yyyy hh:mm:ss a", null));
		System.out.println("System Date in PST: "+formatDateToString(date, "dd MMM yyyy hh:mm:ss a", "PST"));
		System.out.println("System Date in IST: "+formatDateToString(date, "dd MMM yyyy hh:mm:ss a", "IST"));
		System.out.println("System Date in GMT: "+formatDateToString(date, "dd MMM yyyy hh:mm:ss a", "GMT+2"));
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse("2023-06-01 13:00:57", inputFormatter);
		System.out.println("Date Time:"+dateTime);
		int day = 3;
		LocalDate currentDate = LocalDate.now();
		long dayVal = 1;
		System.out.println("dayVal::"+dayVal);

		int n = (int) Math.ceil(((double) day / dayVal));
		System.out.println("count is {}"+ n);
		if (day <= dayVal) {
			System.out.println("sdfdfd");
		} else if (day <= dayVal * n) {
			System.out.println("fgrgretfgfdg");
		}
		
		System.out.println("getDayDiff::"+getDayDiff(1726435706000l));
	}
	
	private static int getDayDiff(long nextBillTime) {
		long currentTime = System.currentTimeMillis();
		long timeDifferenceMillis = Math.abs(currentTime - nextBillTime);
		System.out.println("timeDifferenceMillis::"+timeDifferenceMillis);
		double val = (timeDifferenceMillis / (1000 * 60 * 60 * 24));
		System.out.println("val-"+val);
		return (int) (timeDifferenceMillis / (1000 * 60 * 60 * 24));

	}

}
