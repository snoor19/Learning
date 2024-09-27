package com.spero.learn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LOG3 {
	private static final Logger logger = LogManager.getLogger(LOG3.class.getName());
	public static void print(String jvm) {
		LogTester.initializeYourLogger("./LOGS/LOG3/TLOG_TEST_","--------------This is JVM-3-customized header----------------\n");
		logger.debug("Inside new logger-LOG3 file from-{}",jvm);
	}
}
