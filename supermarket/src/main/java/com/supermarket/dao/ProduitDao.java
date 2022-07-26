package com.supermarket.dao;

import java.util.List;

import com.supermarket.model.Produit;

public interface ProduitDao {

	void insertProduit(Produit produit);

	Produit findProduit(long id);

	List<Produit> selectAllProduit();

	boolean deleteProduit(long id);

	boolean updateProduit(Produit produit);

	long nombreProduit();

}
