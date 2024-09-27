package com.spero.learn;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class SubList {

	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZ");
		DateFormat dft = new SimpleDateFormat("HH:mm:ss.SSS");
		try {
			Date date1 = df.parse("2023-02-16T04:00:00+0530");
			Date date2 = df.parse("2023-02-16T15:00:00+0530");
			Date compare = new Date();
			String start = "17:00:00";
			String end = "08:00:00";
			System.out.println("StartDate::"+dft.format(new Date()));
			System.out.println("EndDate::"+dft.format(new Date()));
			int startVal = Integer.parseInt(start.substring(0, 2));
			int endVal = Integer.parseInt(end.substring(0, 2));
			System.out.println("start::"+startVal);
			System.out.println("end::"+endVal);
			System.out.println("Compare ::"+ (startVal> endVal));
			System.out.println("Is between date ::"+(date1.compareTo(compare) * date2.compareTo(compare) > 0));
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("HHmm");
			System.out.println("HHmm::"+format.format(date1));
			SimpleDateFormat sdf=new SimpleDateFormat("HHmmss");
			Date dateBlackoutStart = sdf.parse("120101");
			System.out.println("dateBlackoutStart::"+dateBlackoutStart);
			Date dateBlackoutEnd = sdf.parse("170101");
			System.out.println("dateBlackoutEnd::"+dateBlackoutEnd);
//			isInBlackout= (date.after(getBlackoutStartTime()) && date.before(getBlackoutEndTime()));
			Date currDate = sdf.parse(sdf.format(new Date()));
			System.out.println("Value::"+(currDate.after(dateBlackoutStart) && currDate.before(dateBlackoutEnd)));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateCh = "2023-02-22 22:26:50";
			Date trTime = dateFormat.parse(dateCh);
			System.out.println("Tr Date::"+trTime);
			SimpleDateFormat sdfmm = new SimpleDateFormat("yyyyMMddHHmmss");
			System.out.println("Date::"+sdfmm.format(trTime));
			
			DateTimeFormatter dft1 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
			LocalDateTime dateTime = LocalDateTime.parse("20210301172900", dft1);
			dateTime = dateTime.minusMinutes(60);
			System.out.println("Date format minus::"+dateTime.format(dft1));
			DateFormat startDF = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String stDate = startDF.format(new Date());
			LocalDateTime dateL = LocalDateTime.now().minusDays(5);
			
			System.out.println("DateLLLLL::"+dateL);
			String startDateL = startDF.format(Date.from(dateL.atZone(ZoneId.systemDefault()).toInstant()));
			System.out.println("startDateL::"+startDateL);
			System.out.println("Start Date for Etisalat::"+stDate);
			String updatedStringTime=null;
			try {
				DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date12 = formatter.parse("20230320134500");
				formatter.setTimeZone(TimeZone.getTimeZone("GMT+7"));
				updatedStringTime = formatter.format(date12);
			}catch (ParseException e) {
				System.out.println("Parse Exception::"+e);
			}
			System.out.println("Updated date is ::"+updatedStringTime);
		} catch (Exception e) {
			System.out.println("error::"+e);
		}
	}

}
