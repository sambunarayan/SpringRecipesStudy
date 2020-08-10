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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.apress.springrecipes.course.dao.CourseDao;
import com.apress.springrecipes.course.dao.HibernateCourseDao;
import com.apress.springrecipes.course.dao.JpaCourseDao;
import com.apress.springrecipes.course.dao.JpaDao;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.apress.springrecipes.course")
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

//	@Bean
//	public CourseDao courseDao(SessionFactory sessionFactory) {
//		return new HibernateCourseDao(sessionFactory);
//	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setHibernateProperties(hibernateProperties());
		sessionFactoryBean.setDataSource(dataSource);
		Resource[] mappingResources = resourcePatternResolver
				.getResources("classpath:com/apress/springrecipes/course/*.hbm.xml");
		sessionFactoryBean.setMappingLocations(mappingResources);
//		sessionFactoryBean.setAnnotatedClasses(Course.class);
//		sessionFactoryBean.setMappingLocations(new ClassPathResource("com/apress/springrecipes/course/Course.hbm.xml"));
		return sessionFactoryBean;
	}

	@Bean
	public JpaDao jpaDao(EntityManagerFactory entityManagerFactory) {
		return new JpaCourseDao(entityManagerFactory);
	}

//	@Bean
//	public EntityManagerFactory entityManagerFactory() throws NamingException {
//		return JndiLocatorDelegate.createDefaultResourceRefLocator()
//				.lookup("jpa/coursePU", EntityManagerFactory.class);
//	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setPersistenceUnitName("course");
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("com.apress.springrecipes.course");
		emf.setJpaVendorAdapter(jpaVendorAdapter());
		return emf;
	}

//	@Bean
//	public LocalEntityManagerFactoryBean entityManagerFactory() {
//		LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
//		emf.setPersistenceUnitName("course");
//		return emf;
//		
//	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}
	
//	@Bean
//	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//		return new JpaTransactionManager(entityManagerFactory);
//	}

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

	private JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(true);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setDatabasePlatform(Oracle10gDialect.class.getName());
		return jpaVendorAdapter;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourcePatternResolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
	}
}
