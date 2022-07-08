package com.supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {

	
	
	public static Connection createconnection() {
		
		
		Connection con = null;
		
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/market", "root", "");
	        } catch (SQLException e) {
	           	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            
	            e.printStackTrace();
	        }
		 
		 System.out.println(" *** Connexion réussie");
	        return con;
		
		
	}
	
	
}
