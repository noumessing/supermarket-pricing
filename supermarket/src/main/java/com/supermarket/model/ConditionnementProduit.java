package com.supermarket.model;

import java.math.BigDecimal;
import java.util.Date;

//Cette classe gère la tarification des produits
//on peut dire: une baguette de pain vaut 2$, 5 baril de petrole valent 3$, 3 kg de lait coutent 2$. Po  
public class ConditionnementProduit {
	
	private long idcp;
	private Produit produit;
	private Conditionnement conditionnement;
	private int quantite; // Pour la plupart des produits çà devrait être 1
	private BigDecimal prix;
	private Date dateEffective;  // Date de tarification du produit
	private int quantitePourBonus;
	private int quantiteBonifiee;
	
	public ConditionnementProduit() {
	
	}
	
	
	
	public ConditionnementProduit(long idcp, Produit produit, Conditionnement conditionnement, int quantite,
			BigDecimal prix, Date dateEffective, int quantitePourBonus, int quantiteBonifiee) {
		super();
		this.idcp = idcp;
		this.produit = produit;
		this.conditionnement = conditionnement;
		this.quantite = quantite;
		this.prix = prix;
		this.dateEffective = dateEffective;
		this.quantitePourBonus = quantitePourBonus;
		this.quantiteBonifiee = quantiteBonifiee;
	}



	public ConditionnementProduit(int quantite, BigDecimal prix, Date dateEffective, int quantitePourBonus,
			int quantiteBonifiee) {
		super();
		this.quantite = quantite;
		this.prix = prix;
		this.dateEffective = dateEffective;
		this.quantitePourBonus = quantitePourBonus;
		this.quantiteBonifiee = quantiteBonifiee;
	}
	
	

	public ConditionnementProduit(Conditionnement conditionnement, int quantite, BigDecimal prix, Date dateEffective,
			int quantitePourBonus, int quantiteBonifiee) {
		super();
		this.conditionnement = conditionnement;
		this.quantite = quantite;
		this.prix = prix;
		this.dateEffective = dateEffective;
		this.quantitePourBonus = quantitePourBonus;
		this.quantiteBonifiee = quantiteBonifiee;
	}


	public ConditionnementProduit(Produit produit, Conditionnement conditionnement, int quantite, BigDecimal prix,
			Date dateEffective, int quantitePourBonus, int quantiteBonifiee) {
		
		this.produit = produit;
		this.conditionnement = conditionnement;
		this.quantite = quantite;
		this.prix = prix;
		this.dateEffective = dateEffective;
		this.quantitePourBonus = quantitePourBonus;
		this.quantiteBonifiee = quantiteBonifiee;
	}
	
	
	public long getIdcp() {
		return idcp;
	}

	public void setIdcp(long idcp) {
		this.idcp = idcp;
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

	public int getQuantitePourBonus() {
		return quantitePourBonus;
	}
	public void setQuantitePourBonus(int quantitePourBonus) {
		this.quantitePourBonus = quantitePourBonus;
	}
	public int getQuantiteBonifiee() {
		return quantiteBonifiee;
	}
	public void setQuantiteBonifiee(int quantiteBonifiee) {
		this.quantiteBonifiee = quantiteBonifiee;
	}
	


}
