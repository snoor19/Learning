package com.spero.learn;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeThreshould {

	public static void main(String[] args) {
		String TRA_DATE_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dateFormat = new SimpleDateFormat(TRA_DATE_FORMATTER);
		Timestamp thresh = null;
		try {
			thresh = new Timestamp(dateFormat.parse("2021-04-04T08:19:48").getTime());
			Timestamp expiry = new Timestamp(sdf.parse("2023-02-05").getTime());
			System.out.println("Value::"+thresh.after(expiry));
			System.out.println("expiry::"+expiry);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("TimeStamp::"+new Timestamp(System.currentTimeMillis()));
		System.out.println("Threshold ::"+thresh);
	}

}
