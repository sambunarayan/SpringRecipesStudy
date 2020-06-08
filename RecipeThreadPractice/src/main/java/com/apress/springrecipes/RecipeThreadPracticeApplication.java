package com.apress.springrecipes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipeThreadPracticeApplication {

	public static void main(String[] args) throws Throwable {
		
		Runnable task = new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000 * 3);
					System.out.println("Done sleeping for a minute, returning! ");
				} catch (Exception e) {}
			}
		};
		
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println("Start executor");
		if (service.submit(task, Boolean.TRUE).get().equals(Boolean.TRUE)) {
			System.out.println("Job has finished!");
		}
		
	}

}
