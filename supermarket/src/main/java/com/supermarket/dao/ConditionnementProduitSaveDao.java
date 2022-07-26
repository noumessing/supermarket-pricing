package com.supermarket.dao;

import java.util.List;

import com.supermarket.model.Conditionnement;
import com.supermarket.model.ConditionnementProduit;
import com.supermarket.model.ConditionnementProduitSave;
import com.supermarket.model.Produit;

public interface ConditionnementProduitSaveDao {
	
	void insertConditionnementProduitSave(ConditionnementProduitSave conditionnementProduit);

	List<ConditionnementProduitSave> findAllConditionnementProduitSave(Produit produit,Conditionnement conditionnement);
	
	List<ConditionnementProduitSave> findAllConditionnementProduitSave(long idProduit,long idConditionnement);

	List<ConditionnementProduitSave> selectAllConditionnementProduitSave();
	
	List<ConditionnementProduitSave> selectConditionnementHistoryParProduit(Produit produit);
	
	List<ConditionnementProduitSave> selectConditionnementParProduit(ConditionnementProduit conditionnementProduit);
	
	List<ConditionnementProduitSave> selectConditionnementHistoryParProduit(long idProduit);
	
	
	long nombreConditionnementProduitSave();

}
