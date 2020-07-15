package com.apress.springrecipes.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class TodoSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	public TodoSecurityConfig() {
//		super(true);
//	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.setName("todos")
				.addScript("classpath:/schema.sql")
				.addScript("classpath:/data.sql")
				.build();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource())
		.usersByUsernameQuery(
				"SELECT userid, password, ENABLED " +
						" FROM USERS WHERE USERNAME = ? ")
		.authoritiesByUsernameQuery(
				"SELECT USERS.USERNAME, AUTHORITIES.AUTHORITY "
				+ "FROM USERS, AUTHORITIES " 
				+ "WHERE USERS.USERNAME = ? AND USERS.USERNAME = AUTHORITIES.USERNAME "
				);
		
//		auth.inMemoryAuthentication()
//		.withUser("user.kim@test.io").password("{noop}user").authorities("USER")
//		.and()
//		.withUser("admin.kim").password("admin").authorities("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.securityContext()
//		.and()
//		.exceptionHandling()
//		.and()
//		.httpBasic()
//		.and()
//		.formLogin().loginPage("/login.jsp");
		
		http.authorizeRequests()
		.antMatchers("/todos*").hasAuthority("USER")
		.antMatchers(HttpMethod.DELETE, "/todos*").hasAuthority("ADMIN")
		.and()
		.formLogin().loginPage("/login.jsp")
		.loginProcessingUrl("/login")
        .failureUrl("/login.jsp?error=true")
        .permitAll()
		.and()
		.logout().logoutSuccessUrl("/logout-success.jsp");
//		
//		HttpSessionCsrfTokenRepository repo = new HttpSessionCsrfTokenRepository();
//		repo.setSessionAttributeName("csrf_token");
//		repo.setParameterName("csrf_token");
//		http.csrf().csrfTokenRepository(repo);
		
//		http.securityContext().and().exceptionHandling().and().servletApi().and().httpBasic().and().formLogin();
	}
}
