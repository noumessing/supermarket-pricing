package com.supermarket.dao;

import java.util.List;

import com.supermarket.model.Produit;

public interface ProduitDao {
	
	public void insertProduit(Produit produit);

	public Produit findProduit(long id) ;

	 public List < Produit > selectAllProduit();
	 
	 public boolean deleteProduit(long id);
	 
	 public boolean updateProduit(Produit produit);
	 
	 public long nombreProduit();

}
