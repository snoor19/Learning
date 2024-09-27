package com.spero.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadPoolClass {

	private LinkedBlockingQueue<String> queue = null;
	ExecutorService executor = null;


	public ThreadPoolClass(int noOfThreads, int maxNoOfTasks) {
		queue = new LinkedBlockingQueue<String>(maxNoOfTasks);
		executor = Executors.newFixedThreadPool(noOfThreads, new ThreadFactoryBuilder().setNameFormat("MTCY_MIGRATOR_%d").build());
		for (int i = 0; i < noOfThreads; i++) {
			MultitenancyMigratorThread migratorThread = new MultitenancyMigratorThread(queue);
			executor.submit(migratorThread);
		}
	}

	public void execute(String record) {
		try {
			if (record != null)
				queue.put(record);
		} catch (InterruptedException e) {
		}
	}

	private class MultitenancyMigratorThread extends Thread {
		private LinkedBlockingQueue<String> queue = null;
		private volatile boolean stop = false;

		public MultitenancyMigratorThread(LinkedBlockingQueue<String> queue) {
			this.queue = queue;
		}

		public void run() {
			ExecutorPoolTesting migrationTool = null;
			try {
				migrationTool = new ExecutorPoolTesting();
				while (!stop) {
					String record = queue.take();
					migrationTool.processRecord(record);
				}
			} catch (Exception e) {
				System.out.println("Exception occured::"+e);
			} catch (Throwable th) {
				System.out.println("Throwable Exception occured::"+th);
			}
		}
	}

	public void shutdown() {
		System.out.println("Shutting down thread pool");
		try {
			while (queue.size() > 0) {
				Thread.sleep(2000); // sleep until queue is empty
			}
			executor.shutdownNow();
		} catch (Exception e) {
			System.out.println("Exception occured::"+e);
		}
	}
}
