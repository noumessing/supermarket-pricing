package com.supermarket.metier;

import java.util.List;

import com.supermarket.dao.ProduitDao;
import com.supermarket.dao.ProduitDaoImpl;
import com.supermarket.model.Produit;


public class ProduitMetierImpl implements ProduitMetier {
	
	private ProduitDao produitDao;

	public ProduitMetierImpl(ProduitDao produitDao) {
		
		this.produitDao = produitDao;
	}
	
	

	public ProduitMetierImpl() {
		super();
		this.produitDao= new ProduitDaoImpl();
		
	}



	public void saveProduit(Produit p) {
		// TODO Auto-generated method stub
		produitDao.insertProduit(p);
	}

	public List<Produit> listProduit() {
	
		return null;
	}



	public Produit findById(long id) {
		// TODO Auto-generated method stub
		return produitDao.findProduit(id);
	}



	public long nombreProduit() {
		// TODO Auto-generated method stub
		return produitDao.nombreProduit();
	}

}
