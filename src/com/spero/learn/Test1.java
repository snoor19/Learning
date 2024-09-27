package com.spero.learn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Test1 {

	static SimpleDateFormat processSDF = new SimpleDateFormat("yyyyMMddHHmm");

	public static void main(String[] args) throws ParseException {
		System.out.println("Check::"+StringUtils.isEmpty("42381911"));
		Test1 test = new Test1();
		System.out.println("Date value::"+test.dateFormatCovertor("01-01-2023 00:01", "dd-MM-yyyy HH:MI", "yyyy-MM-dd"));
	}
	
	private String dateFormatCovertor(String inputDateString, String inputFormat, String outputFormat) {
		String dateString = convertToCamelCase(inputDateString);
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
		LocalDateTime localDateTime = LocalDateTime.parse(dateString, inputFormatter);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(outputFormat);
		return localDateTime.format(outputFormatter);

	}
	
	private static String convertToCamelCase(String inputDateString) {
		String regex = "\\b([A-Za-z]{3})\\b";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(inputDateString);

		StringBuffer result = new StringBuffer();
		while (matcher.find()) {
			String monthAbbreviation = matcher.group(1);
			String replacement = monthAbbreviation.substring(0, 1).toUpperCase()
					+ monthAbbreviation.substring(1).toLowerCase();
			matcher.appendReplacement(result, replacement);
		}
		matcher.appendTail(result);

		return result.toString();
	}

}
