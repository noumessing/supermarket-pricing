package com.supermarket.model;

import java.math.BigDecimal;
import java.util.Date;

public class ConditionnementProduitSave {
	
	private Produit produit;
	private Conditionnement conditionnement;
	private int quantite; // Pour la plupart des produits çà devrait être 1
	private BigDecimal prix;
	private Date dateEffective;  // Date de tarification du produit
	private Date dateFinEffective;  // Date de modification de cette tarification
	private int quantitePourBonus;
	private int quantiteBonifiee;

}
