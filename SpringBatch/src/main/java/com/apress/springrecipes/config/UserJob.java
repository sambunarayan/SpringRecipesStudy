package com.apress.springrecipes.config;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.apress.springrecipes.beans.UserRegistration;
import com.apress.springrecipes.exception.MyException;
import com.apress.springrecipes.process.UserDuplicateCheckProcess;
import com.apress.springrecipes.process.UserRegistrationValidationItemProcessor;

@Configuration
@EnableBatchProcessing
public class UserJob {
	
	private static final String INSERT_REGISTRATION_QUERY 
			= "insert into USER_REGISTRATION (id, FIRST_NAME, LAST_NAME, COMPANY, "
					+ "ADDRESS, CITY, STATE, ZIP, COUNTY, URL, PHONE_NUMBER, FAX)" 
					+ " values (user_registration_seq.nextval, :firstName, :lastName, :company, :address, :city, :state, :zip, :county, :url, :phoneNumber, :fax)";
	
	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory steps;
	
	@Autowired
	private DataSource dataSource;
	
	@Value("classpath:batches/registrations.csv")
	private Resource input;
	
	@Bean("insertIntoDbFormCsvJob")
	public Job insertIntoDbFormCsvJob() {
		return jobs.get("User Registration Import Job")
				.start(step1())
				.build();
	}
	
	public Step step1() {
		return steps.get("User Registration CSV To DB Step")
				.<UserRegistration, UserRegistration>chunk(5)
					.faultTolerant()
						.retryLimit(3).retry(DeadlockLoserDataAccessException.class)
						.noRollback(MyException.class)
				.reader(csvFileReader(input)).readerIsTransactionalQueue()
				.processor(compositeUserRegistrationProcessor())
				.writer(jdbcItemWriter())
				.transactionManager(new DataSourceTransactionManager(dataSource))
				.build();
	}
	
	@Bean
	@StepScope
	public FlatFileItemReader<UserRegistration> csvFileReader(@Value("classpath:batches/#{jobParameters['input.file']}.csv") Resource input) {
		FlatFileItemReader<UserRegistration> itemReader = new FlatFileItemReader<>();
		itemReader.setLineMapper(lineMapper());
		itemReader.setResource(input);
		return itemReader;
	}
	
	@Bean
	public ItemProcessor<UserRegistration, UserRegistration> userRegistrationValidationItemProcessor() {
		return new UserRegistrationValidationItemProcessor();
	}
	
	@Bean
	public ItemProcessor<UserRegistration, UserRegistration> userDuplicateCheckProcess() {
		return new UserDuplicateCheckProcess();
	}
	
	@Bean
	public CompositeItemProcessor<UserRegistration, UserRegistration> compositeUserRegistrationProcessor() {
		List<ItemProcessor<UserRegistration, UserRegistration>> delegates = 
				Arrays.asList(userRegistrationValidationItemProcessor(), userDuplicateCheckProcess());
		CompositeItemProcessor<UserRegistration, UserRegistration> processor = new CompositeItemProcessor<>();
		processor.setDelegates(delegates);
		return processor;
	}
	
	@Bean
	public JdbcBatchItemWriter<UserRegistration> jdbcItemWriter() {
		JdbcBatchItemWriter<UserRegistration> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql(INSERT_REGISTRATION_QUERY);
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return itemWriter;
	}
	
	@Bean
	public DefaultLineMapper<UserRegistration> lineMapper() {
		DefaultLineMapper<UserRegistration> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(tokenizer());
		lineMapper.setFieldSetMapper(fieldSetMapper());
		return lineMapper;
	}
	
	@Bean
	public BeanWrapperFieldSetMapper<UserRegistration> fieldSetMapper() {
		BeanWrapperFieldSetMapper<UserRegistration> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(UserRegistration.class);
		return fieldSetMapper;
	}
	
	@Bean
	public DelimitedLineTokenizer tokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setNames(new String[] {"firstName", "lastName", "company", "address", "city", "state", "zip", "county", "url", "phoneNumber", "fax"});
		return tokenizer;
	}
}
