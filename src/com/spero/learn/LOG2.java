package com.spero.learn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LOG2 {
	private static final Logger logger = LogManager.getLogger(LOG2.class.getName());
	public static void print(String jvm) {
		LogTester.initializeYourLogger("./LOGS/LOG2/TLOG_TEST_","--------------This is JVM-2-customized header----------------\n");
		logger.debug("Inside new logger-LOG2 file from-{}",jvm);
	}
}
