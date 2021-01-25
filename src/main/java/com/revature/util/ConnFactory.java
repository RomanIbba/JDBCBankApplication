package com.revature.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnFactory {

	// private static instance of self
	private static ConnFactory cf = new ConnFactory();

	// private constructor
	private ConnFactory() {
		super();
	}

	// public static synchronized "getter" method
	// synchronized means only one thread can see or run at a time
	public static synchronized ConnFactory getInstance() {
		if (cf == null) {
			cf = new ConnFactory();
		}
		return cf;
	}
	
//	public Connection getConnection() throws URISyntaxException {
//		Connection conn = null;
//		Properties prop = new Properties();
//		try {
//		    URI dbUri = new URI(System.getenv("mrawnzjxabejjz:8101ece8265d14bdafebc06d1ce95e3b7e5aa058318e06c2b74835d4cbfe7eeb@ec2-52-7-39-178.compute-1.amazonaws.com:5432/d9habl9v5pe0g6?sslmode=require"));
//			String username = dbUri.getUserInfo().split(":")[0];
//		    String password = dbUri.getUserInfo().split(":")[1];
//		    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
//			conn = DriverManager.getConnection(dbUrl, username, password);	
//		} catch (SQLException e) {
//			System.out.println("Failed to create connection");
//			e.printStackTrace();
//		}
//		return conn;
////	    return DriverManager.getConnection(dbUrl, username, password);
//	}

	// Methods that do stuff
	public Connection getConnection() {
		Connection conn = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("database.properties"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("password"));
		} catch (SQLException e) {
			System.out.println("Failed to create connection");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
