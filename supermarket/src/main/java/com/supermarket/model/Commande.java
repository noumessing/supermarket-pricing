package com.supermarket.model;

import java.math.BigDecimal;

public class Commande {
	
	private Produit produit;
	private Conditionnement conditionnement;
	private BigDecimal quatite;
	
	
	public Commande(Produit produit, Conditionnement conditionnement, BigDecimal quatite) {
		super();
		this.produit = produit;
		this.conditionnement = conditionnement;
		this.quatite = quatite;
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


	public BigDecimal getQuatite() {
		return quatite;
	}


	public void setQuatite(BigDecimal quatite) {
		this.quatite = quatite;
	}
	

}
