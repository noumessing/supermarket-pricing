package com.supermarket.metier;

import java.math.BigDecimal;
import java.util.List;

import com.supermarket.model.Conditionnement;
import com.supermarket.model.ConditionnementProduit;
import com.supermarket.model.Produit;

public interface CondProduitMetier {
	
	public	List<ConditionnementProduit> getListConditionnement(Produit p); // Liste des tarifs d'un produit avec le conditionnement

	 public ConditionnementProduit addConditionnementProduit(ConditionnementProduit cp); // Ajouter la tarification d'un produit pour un conditionnement
		
	 public	BigDecimal prixUnitaire(Produit p,Conditionnement c);  // Prix unitaire d'un produit
		
	 public	BigDecimal prixCommandeProuit(Produit p,Conditionnement c,int quantite); //

}
