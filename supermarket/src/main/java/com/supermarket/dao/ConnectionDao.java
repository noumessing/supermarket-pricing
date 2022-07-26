package com.supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {

	private static final String DB_URI = "jdbc:mysql://localhost:3306/market";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "";
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public static Connection createconnection() {
		  
		
		Connection con = null;
		
		 try {
	            Class.forName(DB_DRIVER);
	            con = DriverManager.getConnection(DB_URI, DB_USERNAME, DB_PASSWORD);
		 } catch (SQLException | ClassNotFoundException e) {

				e.printStackTrace();
			}
		 
		// System.out.println(" *** Connexion r�ussie");
	        return con;
		
		
	}
	
	
}
