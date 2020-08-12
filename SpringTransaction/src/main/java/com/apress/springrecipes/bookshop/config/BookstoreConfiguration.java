package com.apress.springrecipes.bookshop.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.support.TransactionTemplate;

import com.apress.springrecipes.bookshop.BookShop;
import com.apress.springrecipes.bookshop.TransactionalJdbcBookShop;

@Configuration
public class BookstoreConfiguration {

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setUsername("recipes");
		dataSource.setPassword("recipes");
		return dataSource;
	}

	@Bean
	public BookShop bookShop() {
		TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager());
		
		TransactionalJdbcBookShop bookShop = new TransactionalJdbcBookShop();
		bookShop.setDataSource(dataSource());
		bookShop.setTransactionTemplate(transactionTemplate);
		return bookShop;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		return transactionManager;
	}
}
