package com.spero.learn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LOG1 {
	private static final Logger logger = LogManager.getLogger(LOG1.class.getName());
	public static void print(String jvm) {
		LogTester.initializeYourLogger("./LOGS/LOG1/TLOG_TEST_","--------------This is JVM-1-customized header----------------\n");
		logger.debug("Inside new logger-LOG1 file from-{}",jvm);
	}
}
