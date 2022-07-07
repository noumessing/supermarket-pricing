package com.supermarket.model;

//Cette classe gère les conditionnements: pièce,baguette,kg,litre,baril......
public class Conditionnement {
	
	private long id;
	private String nomConditionnement;
	public Conditionnement() {
		super();
	
	}
	public Conditionnement(long id, String nomConditionnement) {
		super();
		this.id = id;
		this.nomConditionnement = nomConditionnement;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomConditionnement() {
		return nomConditionnement;
	}
	public void setNomConditionnement(String nomConditionnement) {
		this.nomConditionnement = nomConditionnement;
	}
	
}
