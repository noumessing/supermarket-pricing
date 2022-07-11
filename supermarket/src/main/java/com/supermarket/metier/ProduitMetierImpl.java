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
		this.produitDao = new ProduitDaoImpl();

	}

	public void saveProduit(Produit p) {
		
		produitDao.insertProduit(p);
	}

	public List<Produit> listProduit() {

		return null;
	}

	public Produit findById(long id) {
		
		return produitDao.findProduit(id);
	}

	public long nombreProduit() {
		
		return produitDao.nombreProduit();
	}
	
	@Override
	public boolean modifierProduit(Produit p) {
		
		return produitDao.updateProduit(p);
	}


}
