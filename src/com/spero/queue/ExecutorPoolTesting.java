package com.spero.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorPoolTesting {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		ThreadPoolClass executorThreadPool = new ThreadPoolClass(2, 2);
		String[] data = {"Hello","Hi", "Prism","Testing"};
		int i=0;
		List<Future<?>> futures = new ArrayList<>();
		try {
			while (i<data.length) {
				String val = data[i];
				futures.add(executor.submit(new Thread(() -> {
					executorThreadPool.execute(val);
				})));
				i++;
			}
			
			for (Future<?> future : futures) {
				System.out.println("Waiting for is task is done::::"+future.isDone());
				future.get();
			}
		} catch (InterruptedException e) {
			System.out.println("InterruptedException::"+e);
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("ExecutionException::"+e);
			e.printStackTrace();
		}
		System.exit(0);
	}

	public void processRecord(String record) {
		System.out.println("Processing::"+record+","+Thread.currentThread().getName());
	}

}
