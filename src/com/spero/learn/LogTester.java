package com.spero.learn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class LogTester {

	private static final Logger logger = LogManager.getLogger(LogTester.class);
	static DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HH");
	
	public static void main(String[] args) {
		LogTester.initializeYourLogger("./LOGS/LOG/TLOG_TEST_","--------------This is Main-customized header----------------\n");
		logger.debug("Hello from Log4j 2");
		logger.debug("This is a Debug Message!");
		logger.info("This is an Info Message!");
		long currentMilliseconds = System.currentTimeMillis();
        
        Instant instant = Instant.ofEpochMilli(currentMilliseconds);
        instant = instant.plus(15, ChronoUnit.MINUTES);
        
        long newMilliseconds = instant.toEpochMilli();
		while (newMilliseconds > System.currentTimeMillis()) {
			LOG1.print("Main JVM");
			LOG2.print("Main JVM");
			LOG3.print("Main JVM");
			try {
				Thread.sleep(15000);
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
	
	public static void initializeYourLogger(String fileName, String header) {
		Date now = new Date();
//		String pattern = "%d %p %c [%t] %m%n";
		String pattern = "%d{yyyy-MM-dd HH:mm:ss,SSS}{GMT-4} %m%n";
//		fileName = fileName +formatter.format(now)+"0000.tmp";
		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        builder.setStatusLevel(Level.DEBUG);
        builder.setConfigurationName("DefaultLogger");
        
        // create a console appender
        AppenderComponentBuilder appenderBuilder = builder.newAppender("Console", "CONSOLE").addAttribute("target",
                ConsoleAppender.Target.SYSTEM_OUT);
        appenderBuilder.add(builder.newLayout("PatternLayout")
                .addAttribute("pattern", pattern));
//                .addAttribute("ConversionPattern", "%d{ISO8601}{GMT-4}   %-5p [%t] %c: %m%n"));
        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.DEBUG);
        rootLogger.add(builder.newAppenderRef("Console"));

        builder.add(appenderBuilder);

        // create a rolling file appender
        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
                .addAttribute("pattern", pattern)
                .addAttribute("header", header);
        
		/*
		 * DailyRollingFileAppender appender = new DailyRollingFileAppender();
		 * appender.setDatePattern("'.'yyyy-MM-dd-HH");
		 * 
		 * TimeBasedTriggeringPolicy logFilePolicy = new TimeBasedTriggeringPolicy();
		 */
        
        ComponentBuilder triggeringPolicy = builder.newComponent("Policies")
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "50KB"))
                .addComponent(builder.newComponent("TimeBasedTriggeringPolicy"));
        appenderBuilder = builder.newAppender("LogToRollingFile", "RollingFile")
                .addAttribute("fileName", fileName)
                .addAttribute("DatePattern", "_yyyy-MM-dd.log")
//                .addAttribute("filePattern", fileName+"_%d{MM_dd_yy_HH_mm}0000.txt")
                .addAttribute("filePattern", fileName+"_%d{yyyyMMdd_HHmm}{GMT-4}00.txt")
                .add(layoutBuilder)
                .addComponent(triggeringPolicy);
        builder.add(appenderBuilder);
        rootLogger.add(builder.newAppenderRef("LogToRollingFile"));
        builder.add(rootLogger);
        Configurator.reconfigure(builder.build());
    }
	
	public static void initializeFileAppender() {
		String filename = "app.log";
		String pattern = "%d %p %c [%t] %m%n";

		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

		builder.setStatusLevel(Level.DEBUG);
		builder.setConfigurationName("DefaultFileLogger");

		// set the pattern layout and pattern
		LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
		                .addAttribute("pattern", pattern);
		
		 RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.DEBUG);
	        rootLogger.add(builder.newAppenderRef("Console"));

		// create a file appender
		AppenderComponentBuilder appenderBuilder = builder.newAppender("LogToFile", "File")
		                .addAttribute("fileName", filename)
		                .add(layoutBuilder);

		builder.add(appenderBuilder);
		rootLogger.add(builder.newAppenderRef("LogToFile"));
		builder.add(rootLogger);
		Configurator.reconfigure(builder.build());
	}

}
