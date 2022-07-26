package com.supermarket.model;

import java.math.BigDecimal;
import java.util.Date;

public class ConditionnementProduitSave {
	
	private long id;
	private ConditionnementProduit conditionnementProduit;
	private Date dateFinEffective; // Date de modification de cette tarification
	

	public ConditionnementProduitSave() {
		super();
	}


	public ConditionnementProduitSave(long id, ConditionnementProduit conditionnementProduit, Date dateFinEffective) {
		super();
		this.id = id;
		this.conditionnementProduit = conditionnementProduit;
		this.dateFinEffective = dateFinEffective;
	}


	public ConditionnementProduitSave(ConditionnementProduit conditionnementProduit, Date dateFinEffective) {
		super();
		this.conditionnementProduit = conditionnementProduit;
		this.dateFinEffective = dateFinEffective;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public ConditionnementProduit getConditionnementProduit() {
		return conditionnementProduit;
	}


	public void setConditionnementProduit(ConditionnementProduit conditionnementProduit) {
		this.conditionnementProduit = conditionnementProduit;
	}


	public Date getDateFinEffective() {
		return dateFinEffective;
	}


	public void setDateFinEffective(Date dateFinEffective) {
		this.dateFinEffective = dateFinEffective;
	}


	@Override
	public String toString() {
		return "ConditionnementProduitSave [id=" + id + ", conditionnementProduit=" + conditionnementProduit.toString()
				+ ", dateFinEffective=" + dateFinEffective + "]";
	}


}
