package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static Connection conn = null;
	
	private ConnectionUtil() {
		super();
	}
	
	public static Connection getConnection() {
		try {
			if(conn != null && !conn.isClosed()) {
				return conn;
			}
		}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("WE FAILD TO REUSE A CONNECTION");
				return null;
			}
			
			String url = "jdbc:postgresql://training.cio3nwbbkrum.us-east-1.rds.amazonaws.com:5432/jwa200810";
			String username = "root";
			String password = "password";
			
			try {
				conn = DriverManager.getConnection(url,username,password);
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("WE FAILED TO GET A CONNECTION!");
				return null;
			}
		return conn;
		
	}
}
