package com.supermarket.metier;

import java.util.List;

import com.supermarket.model.Produit;

public interface ProduitMetier {
	
	 void saveProduit(Produit p);
	
	 List<Produit> listProduit();
	
	 Produit findById(long id);
	
	 long nombreProduit();
	
	 boolean modifierProduit(Produit p);
}
