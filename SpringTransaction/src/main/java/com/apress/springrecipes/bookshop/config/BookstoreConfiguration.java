package com.apress.springrecipes.bookshop.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.apress.springrecipes.bookshop.BookShop;
import com.apress.springrecipes.bookshop.JdbcBookShop;

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
		JdbcBookShop bookShop = new JdbcBookShop();
		bookShop.setDataSource(dataSource());
		return bookShop;
	}
}
