package com.apress.springrecipes.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
//@EnableBatchProcessing
@ComponentScan("com.apress.springrecipes.scheduler")
@EnableScheduling
@EnableAsync
public class BatchConfiguration {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}
	
	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setContinueOnError(true);
		databasePopulator.addScript(new ClassPathResource("org/springframework/batch/core/schema-oracle10g.sql"));
		databasePopulator.addScript(new ClassPathResource("sql/reset_user_registration.sql"));
		databasePopulator.addScript(new ClassPathResource("sql/user_registration.sql"));
		return databasePopulator;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public JobRepositoryFactoryBean jobRepository() {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(dataSource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager());
		return jobRepositoryFactoryBean;
	}
	
	@Bean
	public JobLauncher jobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(jobRepository().getObject());
		return jobLauncher;
	}
	
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor() {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry());
		return jobRegistryBeanPostProcessor;
	}
	
	@Bean
	public JobRegistry jobRegistry() {
		return new MapJobRegistry();
	}
	
	@Bean
	public ThreadPoolTaskScheduler taskExecutor() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setThreadGroupName("batch-scheduler");
		taskScheduler.setPoolSize(10);
		return taskScheduler;
	}
}
