package com.apress.springrecipes.main;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

public class TaskExecutorMain {

	public static void main(String[] args) throws Throwable {
		TaskExecutor exe = new SimpleAsyncTaskExecutor();
		exe.execute(()->{
			System.out.println("task is started");
		});
		System.out.println("next line");
	}

}
