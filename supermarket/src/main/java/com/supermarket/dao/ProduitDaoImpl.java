package com.supermarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.supermarket.model.Produit;

public class ProduitDaoImpl implements ProduitDao {
	
	private static final String INSERT_PRODUIT_SQL = "INSERT INTO produit" + "  (nom_produit) VALUES " +
	        " (?);";

	    private static final String SELECT_PRODUIT_BY_ID = "select id,nom_produit from produit where id =?";
	    private static final String SELECT_ALL_PRODUITS = "select * from produit";
	    private static final String DELETE_PRODUITS_SQL = "delete from produit where id = ?;";
	    private static final String UPDATE_PRODUITS_SQL = "update produit set nom_produit = ? where id = ?;";
	    private static final String SELECT_NB_PRODUIT = "select count(*) from produit;";

	public void insertProduit(Produit produit) {
		Connection con=null;
		 con =ConnectionDao.createconnection();
		 try {
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_PRODUIT_SQL);
			preparedStatement.setString(1, produit.getNomProduit());
			System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            	} catch (SQLException e) {
		
            			
			e.printStackTrace();
		}
		
		 
		 
	}

	public Produit findProduit(long id) {

		Connection con=null;
		 con =ConnectionDao.createconnection();
		 Produit produit = null;
        
       
		 try { 
            PreparedStatement preparedStatement = con.prepareStatement(SELECT_PRODUIT_BY_ID); 
            
				preparedStatement.setLong(1, id);
			
            System.out.println(preparedStatement);
          
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String nomProduit = rs.getString(2);
                
                produit = new Produit(id, nomProduit);
            }
	} catch (SQLException e) { }
        
		return produit;
	}

	public long nombreProduit() {
		Connection con=null;
		 con =ConnectionDao.createconnection();
		 long nb=-1;
		 try {
			 PreparedStatement preparedStatement = con.prepareStatement(SELECT_NB_PRODUIT); 

         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
             nb=  rs.getLong(1);}
           
            	} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return nb;}
	
	public List<Produit> selectAllProduit() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean deleteProduit(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateProduit(Produit produit) {
		// TODO Auto-generated method stub
		return false;
	}

}
