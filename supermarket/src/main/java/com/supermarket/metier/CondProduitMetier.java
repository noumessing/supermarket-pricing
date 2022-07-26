package com.supermarket.metier;

import java.math.BigDecimal;
import java.util.List;

import com.supermarket.model.Conditionnement;
import com.supermarket.model.ConditionnementProduit;
import com.supermarket.model.Produit;

public interface CondProduitMetier {
	
	 List<ConditionnementProduit> getListConditionnement(Produit p); // Liste des tarifs d'un produit avec le conditionnement

	  void  addConditionnementProduit(ConditionnementProduit cp); // Ajouter la tarification d'un produit pour un conditionnement
		
	  BigDecimal prixUnitaire(Produit p,Conditionnement c);  // Prix unitaire d'un produit
	 
	  ConditionnementProduit tarifProduit(long idProduit,long idConditionnement); // tarification d'un produit donné suivant un conditionnement
		
	  BigDecimal prixCommandeProuit(Produit p,Conditionnement c,int quantite); //
	 
	  boolean modifierTarifProduit(ConditionnementProduit conditionnementProduit);
	 
	  long nombreTarification();

	  int getQuantiteDestockee(Produit p, Conditionnement c, int quantite);
	  
	  int getQuantiteTotalBonus (Produit p, Conditionnement c, BigDecimal quantite);
}
