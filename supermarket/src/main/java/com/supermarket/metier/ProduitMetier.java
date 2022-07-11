package com.supermarket.metier;

import java.util.List;

import com.supermarket.model.Produit;

public interface ProduitMetier {
	
	public void saveProduit(Produit p);
	
	public List<Produit> listProduit();
	
	public Produit findById(long id);
	
	public long nombreProduit();
	
	public boolean modifierProduit(Produit p);
}
