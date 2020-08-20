package com.apress.springrecipes.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class CustomBatchConfigurer extends DefaultBatchConfigurer {
	private final TaskExecutor taskExecutor;
	
	public CustomBatchConfigurer(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
	@Override
	protected JobLauncher createJobLauncher() throws Exception {
		System.out.println(":::::::::: CustomBatchConfigurer createJobLauncher ::::::::::");
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(getJobRepository());
		jobLauncher.setTaskExecutor(this.taskExecutor);
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}
}
