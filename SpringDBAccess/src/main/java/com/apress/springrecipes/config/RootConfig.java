package com.apress.springrecipes.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.apress.springrecipes.dao.JdbcVehicleDao;
import com.apress.springrecipes.dao.VehicleDao;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan("com.apress.springrecipes.config")
public class RootConfig {

	@Bean
	public VehicleDao vehicleDao(JdbcTemplate jdbcTemplate) {
		JdbcVehicleDao jdbcVehicleDao = new JdbcVehicleDao();
		jdbcVehicleDao.setDataSource(dataSource());
		return jdbcVehicleDao;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
//		dataSource.setUsername("recipes");
//		dataSource.setPassword("recipes");

		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
		hikariConfig.setUsername("recipes");
		hikariConfig.setPassword("recipes");

		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		// minimum pool size
		dataSource.setMinimumIdle(2);
		// maximum active connections
		dataSource.setMaximumPoolSize(5);

		return dataSource;
	}
}
