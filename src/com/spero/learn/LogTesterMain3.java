package com.spero.learn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTesterMain3 {
	
	private static final Logger logger = LogManager.getLogger(LogTesterMain3.class);
	static DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HH");

	public static void main(String[] args) {
		LogTester.initializeYourLogger("./LOGS/LOG/TLOG_TEST_","--------------This is Main-JVM-3-customized header----------------\n");
		logger.debug("Hello from Log4j2 Main-3");
		logger.debug("This is a Debug Message!");
		logger.info("This is an Info Message!");
		long currentMilliseconds = System.currentTimeMillis();
        
        Instant instant = Instant.ofEpochMilli(currentMilliseconds);
        instant = instant.plus(15, ChronoUnit.MINUTES);
        
        long newMilliseconds = instant.toEpochMilli();
		while (newMilliseconds > System.currentTimeMillis()) {
			LOG1.print("JVM-3");
			LOG2.print("JVM-3");
			LOG3.print("JVM-3");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			System.out.println(100/0);
		}
		catch(Exception e) {
			logger.error("Error Occured", e);
		}
		
		//logger.error("And here comes the Error Message!", new RuntimeException("Run Run Run"));
	
	}

}
