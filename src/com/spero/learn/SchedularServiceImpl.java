package com.spero.learn;

import java.sql.Timestamp;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedularServiceImpl {
	
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
		scheduledExecutorService.scheduleAtFixedRate(SchedularServiceImpl.processURLReco("1"), 5, 60, TimeUnit.SECONDS);
		scheduledExecutorService.scheduleAtFixedRate(SchedularServiceImpl.processURLReco("2"), 5, 60, TimeUnit.SECONDS);
		scheduledExecutorService.scheduleAtFixedRate(SchedularServiceImpl.processURLReco("3"), 5, 60, TimeUnit.SECONDS);
	}
	
	public static Runnable processURLReco(String recoDetails) {
		System.out.println("Entered URLRECO");
		try {
			return () -> {
				System.out.println("Print::->"+recoDetails+"-"+new Timestamp(System.currentTimeMillis()));
			};
		} catch (Exception e) {
			return () -> {
				System.out.println("Print Exception::->"+recoDetails+"-"+new Timestamp(System.currentTimeMillis()));
			};
		}
	}
}
