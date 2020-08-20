package com.apress.springrecipes.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.apress.springrecipes.dao.UserDao;
import com.apress.springrecipes.dao.UserRegistrationDao;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:batch.properties")
@ComponentScan("com.apress.springrecipes.beans")
//@EnableJpaRepositories("com.apress.springrecipes.dao")
public class DaoConfiguration {
	
	@Autowired
	private Environment env;

	@Bean
	public UserDao userDao() {
		return new UserRegistrationDao();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		return emf;
	}
	
	private JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true);
		return jpaVendorAdapter;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager();
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getRequiredProperty("dataSource.url"));
		dataSource.setUsername(env.getRequiredProperty("dataSource.username"));
		dataSource.setPassword(env.getRequiredProperty("dataSource.password"));
		dataSource.setDriverClassName(env.getRequiredProperty("datasource.data-source-class-name"));
		return dataSource;
	}
}
