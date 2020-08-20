package com.apress.springrecipes;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.apress.springrecipes.scheduler.JobScheduler;

public class Main {

	public static void main(String[] args) throws Throwable {
		ApplicationContext context = new AnnotationConfigApplicationContext("com.apress.springrecipes.config");
		
		JobRegistry jobRegistry = context.getBean("jobRegistry", JobRegistry.class);
		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		JobRepository jobRepository = context.getBean("jobRepository", JobRepository.class);
		
		System.out.println("jobRegistry: " + jobRegistry);
		System.out.println("jobLauncher: " + jobLauncher);
		System.out.println("jobRepository: " + jobRepository);
		
//		for (String n : context.getBeanDefinitionNames()) {
//			System.out.println(n);
//		}
		
		JobLauncher joblauncher = context.getBean("jobLauncher", JobLauncher.class);
		Job job = context.getBean("insertIntoDbFormCsvJob", Job.class);
//		JobExecution jobExecution = jobLauncher.run(job, new JobParameters());
		
//		Job job2 = context.getBean("updateDbFormCsvJob", Job.class);
//		jobExecution = joblauncher.run(job2, new JobParameters());  
	}

}
