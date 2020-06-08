package com.apress.springrecipes;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {
	public static void main(String[] args) throws Throwable {
		Runnable task = new DemonstrationRunnable();
		
		ExecutorService cachedThreadPoolExecutorService = Executors.newCachedThreadPool();
		if (cachedThreadPoolExecutorService.submit(task).get() == null) {
			System.out.printf("The cachedThreadPoolExecutorService has succeeded at %s \n", new Date());
		}
		
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(100);
		if (fixedThreadPool.submit(task).get() == null) {
			System.out.printf("fiedThreadPool has succeeded at %s \n", new Date());
		}
		
		ExecutorService singleThreadExecutorService = Executors.newSingleThreadExecutor();
		if (singleThreadExecutorService.submit(task).get() == null) {
			System.out.printf("singleThreadExecutorService has succeeded at %s \n", new Date());
		}
		
		ExecutorService es = Executors.newCachedThreadPool();
		if (es.submit(task, Boolean.TRUE).get().equals(Boolean.TRUE)) {
			System.out.println("Job has finished!");
		}
		
		ScheduledExecutorService scheduledThreadExecutorService = Executors.newScheduledThreadPool(10);
		if (scheduledThreadExecutorService.schedule(task, 30, TimeUnit.SECONDS).get() == null) {
			System.out.printf("scheduledThreadExecutorService has succeeded at %s \n", new Date());
		}
		
		scheduledThreadExecutorService.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
	}

}
