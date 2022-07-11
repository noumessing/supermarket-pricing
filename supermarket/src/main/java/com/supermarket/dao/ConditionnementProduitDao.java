package com.supermarket.dao;

import java.util.List;

import com.supermarket.model.ConditionnementProduit;

public interface ConditionnementProduitDao {
	
	public void insertConditionnementProduit(ConditionnementProduit produit);

	public ConditionnementProduit findConditionnementProduit(long idProduit,long idConditionnement);

	public List<ConditionnementProduit> selectAllConditionnementProduit();
	
	public List<ConditionnementProduit> selectConditionnementParProduit(long idProduit);

	public boolean deleteConditionnementProduit(long id);

	public boolean updateConditionnementProduit(ConditionnementProduit conditionnementProduit);
	
	public long nombreConditionnementProduit();
}
