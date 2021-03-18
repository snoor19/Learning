package com.spero.learn;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimeThreshould {

	public static void main(String[] args) {
		String TRA_DATE_FORMATTER = "yyyyMMddHHmmss";
		SimpleDateFormat dateFormat = new SimpleDateFormat(TRA_DATE_FORMATTER);
		String thresh = dateFormat.format(new Timestamp(System.currentTimeMillis()));
		System.out.println("TimeStamp::"+new Timestamp(System.currentTimeMillis()));
		System.out.println("Threshold ::"+thresh);
	}

}
