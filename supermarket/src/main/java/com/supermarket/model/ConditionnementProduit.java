package com.supermarket.model;

import java.math.BigDecimal;
import java.util.Date;

//Cette classe gère la tarification des produits
//on peut dire: une baguette de pain vaut 2$, 5 baril de petrole valent 3$, 3 kg de lait coutent 2$. Po  
public class ConditionnementProduit {
	
	private Produit produit;
	private Conditionnement conditionnement;
	private int quantite; // Pour la plupart des produits çà devrait être 1
	private BigDecimal prix;
	private Date dateEffective;  // Date de tarification du produit
	
	
	public ConditionnementProduit() {
	
	}
	public ConditionnementProduit(Produit produit, Conditionnement conditionnement, int quantite, BigDecimal prix,
			Date dateEffective) {
	
		this.produit = produit;
		this.conditionnement = conditionnement;
		this.quantite = quantite;
		this.prix = prix;
		this.dateEffective = dateEffective;
	}
	
	
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public Conditionnement getConditionnement() {
		return conditionnement;
	}
	public void setConditionnement(Conditionnement conditionnement) {
		this.conditionnement = conditionnement;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public BigDecimal getPrix() {
		return prix;
	}
	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}
	public Date getDateEffective() {
		return dateEffective;
	}
	public void setDateEffective(Date dateEffective) {
		this.dateEffective = dateEffective;
	}

	


}
