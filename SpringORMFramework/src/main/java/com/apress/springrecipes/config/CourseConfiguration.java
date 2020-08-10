package com.apress.springrecipes.config;

import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.Oracle10gDialect;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import com.apress.springrecipes.course.dao.CourseDao;
import com.apress.springrecipes.course.dao.HibernateCourseDao;
import com.apress.springrecipes.course.dao.JpaCourseDao;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class CourseConfiguration implements ResourceLoaderAware {
	
	private ResourcePatternResolver resourcePatternResolver;
	
	@Bean
	public DataSource dataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setUsername("recipes");
		dataSource.setPassword("recipes");
		dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
		dataSource.setMinimumIdle(2);
		dataSource.setMaximumPoolSize(5);
		return dataSource;
	}
	
	@Bean
	public CourseDao courseDao(SessionFactory sessionFactory) {
		return new HibernateCourseDao(sessionFactory);
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		sessionFactoryBean.setDataSource(dataSource);
		Resource[] mappingResources = resourcePatternResolver.getResources(
				"classpath:com/apress/springrecipes/course/*.hbm.xml");
		sessionFactoryBean.setMappingLocations(mappingResources);
//		sessionFactoryBean.setAnnotatedClasses(Course.class);
//		sessionFactoryBean.setMappingLocations(new ClassPathResource("com/apress/springrecipes/course/Course.hbm.xml"));
		return sessionFactoryBean;
	}
	
//	@Bean
//	public CourseDao courseDao(EntityManagerFactory entityManagerFactory) {
//		return new JpaCourseDao(entityManagerFactory);
//	}
	
	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
		emf.setPersistenceUnitName("course");
		return emf;
		
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
//		properties.setProperty(AvailableSettings.URL, "jdbc:oracle:thin:@localhost:1521:XE");
//		properties.setProperty(AvailableSettings.USER, "recipes");
//		properties.setProperty(AvailableSettings.PASS, "recipes");
		properties.setProperty(AvailableSettings.DIALECT, Oracle10gDialect.class.getName());
		properties.setProperty(AvailableSettings.SHOW_SQL, String.valueOf(true));
		properties.setProperty(AvailableSettings.HBM2DDL_AUTO, "update");
		return properties;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
	}
}
