package com.apress.springrecipes.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class H2TestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(H2TestApplication.class, args);
	}

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Override
    public void run(String... strings) throws Exception {
		jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");
    
        jdbcTemplate.update("INSERT INTO customers(first_name, last_name) VALUES ('John','Woo')");
	}
}
