package com.spero.learn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatCheck {

	public static void main(String[] args) {
		try {
			String trans_time = "20200728133551";
			SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			System.out.println("Form Date::"+form.format(new Date()));
			/*
			 * SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd"); String
			 * reformattedDate = myFormat.format(form.parse(trans_time));
			 * System.out.println("Reformad Date::"+reformattedDate);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
