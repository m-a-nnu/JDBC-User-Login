package com.ty.car.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionObject {

	static String url = "jdbc:mysql://localhost:3306/car_jdbc";
	static String user = "root";
	static String password = "root";
	static {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
    {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return con;
	}
}
