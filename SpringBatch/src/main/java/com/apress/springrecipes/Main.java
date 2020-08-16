package com.apress.springrecipes;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.config.BatchConfiguration;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(BatchConfiguration.class);
		
		JobRegistry jobRegistry = context.getBean("jobRegistry", JobRegistry.class);
		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		JobRepository jobRepository = context.getBean("jobRepository", JobRepository.class);
		
		System.out.println("jobRegistry: " + jobRegistry);
		System.out.println("jobLauncher: " + jobLauncher);
		System.out.println("jobRepository: " + jobRepository);

	}

}
