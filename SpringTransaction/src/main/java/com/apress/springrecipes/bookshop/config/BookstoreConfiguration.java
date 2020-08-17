package com.apress.springrecipes.bookshop.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.apress.springrecipes.bookshop.AnnotationJdbcBookShop;
import com.apress.springrecipes.bookshop.BookShop;
import com.apress.springrecipes.bookshop.BookShopCashier;
import com.apress.springrecipes.bookshop.Cashier;

@Configuration
@EnableTransactionManagement
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
//		TransactionTemplate transactionTemplate = new TransactionTemplate();
//		transactionTemplate.setTransactionManager(transactionManager());
//		
//		TransactionalJdbcBookShop bookShop = new TransactionalJdbcBookShop();
//		bookShop.setDataSource(dataSource());
//		bookShop.setTransactionTemplate(transactionTemplate);
		AnnotationJdbcBookShop bookShop = new AnnotationJdbcBookShop();
		bookShop.setDataSource(dataSource());
		return bookShop;
	}
	
	@Bean
	public Cashier cashier() {
		BookShopCashier cashier = new BookShopCashier();
		cashier.setBookShop(bookShop());
		return cashier;
	}
//
//	@Bean
//	public DataSourceTransactionManager transactionManager() {
//		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
//		transactionManager.setDataSource(dataSource());
//		return transactionManager;
//	}
}
