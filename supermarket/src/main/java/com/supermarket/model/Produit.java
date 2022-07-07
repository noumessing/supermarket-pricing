package com.supermarket.model;

public class Produit {
	
	private long id;
	private String nomProduit;
	
	
	public Produit() {
		super();
	}


	

	public Produit(long id, String nomProduit) {
		super();
		this.id = id;
		this.nomProduit = nomProduit;
	}

	
	public Produit( String nomProduit) {
		super();
		this.nomProduit = nomProduit;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNomProduit() {
		return nomProduit;
	}


	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nomProduit == null) ? 0 : nomProduit.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produit other = (Produit) obj;
		if (id != other.id)
			return false;
		if (nomProduit == null) {
			if (other.nomProduit != null)
				return false;
		} else if (!nomProduit.equals(other.nomProduit))
			return false;
		return true;
	}


}
