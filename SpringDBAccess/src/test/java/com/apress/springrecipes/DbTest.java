package com.apress.springrecipes;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DbTest {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnection() {
		try (Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:XE",
				"recipes",
				"recipes")) {
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
