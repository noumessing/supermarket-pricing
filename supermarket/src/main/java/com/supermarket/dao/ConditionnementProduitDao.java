package com.supermarket.dao;

import java.util.List;

import com.supermarket.model.ConditionnementProduit;

public interface ConditionnementProduitDao {

	void insertConditionnementProduit(ConditionnementProduit produit);

	ConditionnementProduit findConditionnementProduit(long idProduit, long idConditionnement);

	List<ConditionnementProduit> selectAllConditionnementProduit();

	List<ConditionnementProduit> selectConditionnementParProduit(long idProduit);

	boolean deleteConditionnementProduit(long id);

	boolean updateConditionnementProduit(ConditionnementProduit conditionnementProduit);

	long nombreConditionnementProduit();
}
