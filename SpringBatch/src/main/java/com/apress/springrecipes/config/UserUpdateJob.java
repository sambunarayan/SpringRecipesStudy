package com.apress.springrecipes.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.apress.springrecipes.batch.reader.UpdateUserServiceItemReader;
import com.apress.springrecipes.batch.writer.UpdateUserServiceItemWriter;
import com.apress.springrecipes.beans.UserRegistration;
import com.apress.springrecipes.service.UserUpdateService;
import com.apress.springrecipes.service.UserUpdateServiceImpl;

@Configuration
@EnableBatchProcessing
public class UserUpdateJob {
	
	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory steps;
	
	@Value("classpath:batches/updateusers.csv")
	private Resource input;

	@Bean("updateDbFormCsvJob")
	public Job updateDbFormCsvJob() {
		return jobs.get("User Update Import Job")
				.start(userUpdatestep1())
				.build();
	}
	
	@Bean 
	public Step userUpdatestep1() {
		return steps.get("User Update CSV To DB Step")
		.<UserRegistration, UserRegistration>chunk(5)
		.reader(updateUserServiceItemReader())
		.writer(updateUserServiceItemWriter())
		.build();
	}
	
	@Bean
	public UpdateUserServiceItemReader updateUserServiceItemReader() {
		return new UpdateUserServiceItemReader(userUpdateService());
	}
	
	@Bean
	public UpdateUserServiceItemWriter updateUserServiceItemWriter() {
		return new UpdateUserServiceItemWriter(userUpdateService());
	}
	
	@Bean
	public UserUpdateService userUpdateService() {
		return new UserUpdateServiceImpl(input);
	}
}
