package com.apress.springrecipes.config;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@SpringBootApplication
//@EnableBatchProcessing
@ComponentScan("com.apress.springrecipes.springbatch")
@PropertySource("application.properties")
public class BatchConfiguration {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getRequiredProperty("spring.datasource.url"));
		dataSource.setUsername(env.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
		dataSource.setDriverClassName(env.getRequiredProperty("spring.datasource.driver-class-name"));
		return dataSource;
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer() {
		DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource());
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}
	
	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		databasePopulator.setContinueOnError(true);
		databasePopulator.addScript(new ClassPathResource("org/springframework/batch/core/schema-h2.sql"));
		databasePopulator.addScript(new ClassPathResource("sql/reset_user_registration.sql"));
		databasePopulator.addScript(new ClassPathResource("sql/user_registration.sql"));
		return databasePopulator;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public JobRepositoryFactoryBean jobRepository() {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDataSource(dataSource());
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
}
