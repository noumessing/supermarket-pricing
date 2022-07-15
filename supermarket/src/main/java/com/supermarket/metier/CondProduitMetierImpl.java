package com.supermarket.metier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.supermarket.dao.ConditionnementProduitDao;
import com.supermarket.dao.ConditionnementProduitDaoImpl;
import com.supermarket.model.Conditionnement;
import com.supermarket.model.ConditionnementProduit;
import com.supermarket.model.Produit;

public class CondProduitMetierImpl implements CondProduitMetier {
	
	private final ConditionnementProduitDao conditionnementProduitDao;

	public CondProduitMetierImpl() {
		super();
		this.conditionnementProduitDao = new ConditionnementProduitDaoImpl();
	}

	public CondProduitMetierImpl(ConditionnementProduitDao conditionnementProduitDao) {
		super();
		this.conditionnementProduitDao = conditionnementProduitDao;
	}

	@Override
	public List<ConditionnementProduit> getListConditionnement(Produit p) {

		return conditionnementProduitDao.selectConditionnementParProduit(p.getId());
	}

	@Override
	public void addConditionnementProduit(ConditionnementProduit cp) {

		conditionnementProduitDao.insertConditionnementProduit(cp);
		;
	}

	@Override // Prix unitaire d'un produit
	public BigDecimal prixUnitaire(Produit p, Conditionnement c) {
		ConditionnementProduit tarifProduit = tarifProduit(p.getId(), c.getId());
		BigDecimal pu = new BigDecimal(0); // on initialise le prix unitaire � z�ro
		BigDecimal qte = new BigDecimal(tarifProduit.getQuantite());
		pu = tarifProduit.getPrix().divide(qte);

		pu = pu.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return pu;

	}

	 // Prix de revient d'un produit command� suivant son conditionnement et la
				// quantit�, on doit tenir compte d'�ventuels bonus qui ne doivent pas �tre
				// factur�s
	@Override
	public BigDecimal prixCommandeProuit(Produit p, Conditionnement c, int quantite) {
		BigDecimal prixCommandeProduit = new BigDecimal(0);

		BigDecimal pu = prixUnitaire(p, c); // on d�duit le prix unitaire
		int quantiteAfacturer = 0;
		ConditionnementProduit tarifProduit = tarifProduit(p.getId(), c.getId());
		int qtebonus = tarifProduit.getQuantitePourBonus();

		if (qtebonus > 0) {
			quantiteAfacturer = quantite - quantite % qtebonus; // quantite r�elle � facturer s'il y a un bonus}
		} else {
			quantiteAfacturer = quantite;
		}

		BigDecimal quantiteAPayer = new BigDecimal(quantiteAfacturer);
		prixCommandeProduit = pu.multiply(quantiteAPayer);
		prixCommandeProduit = prixCommandeProduit.setScale(2, BigDecimal.ROUND_HALF_EVEN);

		return prixCommandeProduit;
	}

	@Override
	public ConditionnementProduit tarifProduit(long idProduit, long idConditionnement) {

		return conditionnementProduitDao.findConditionnementProduit(idProduit, idConditionnement);
	}

	@Override
	public boolean modifierTarifProduit(ConditionnementProduit conditionnementProduit) {

		conditionnementProduit.setDateEffective(new Date());
		return conditionnementProduitDao.updateConditionnementProduit(conditionnementProduit);
	}

	@Override
	public long nombreTarification() {

		return conditionnementProduitDao.nombreConditionnementProduit();
	}


}
