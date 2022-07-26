package com.supermarket.metier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
		BigDecimal pu = new BigDecimal(0); // on initialise le prix unitaire à zéro
		 if ( tarifProduit != null)
		{BigDecimal qte = new BigDecimal(tarifProduit.getQuantite());
		pu = tarifProduit.getPrix().divide(qte, 2, RoundingMode.HALF_UP);

		pu = pu.setScale(2, BigDecimal.ROUND_HALF_EVEN);}
		return pu;

	}



	// Prix de revient d'un produit commandé suivant son conditionnement et la quantité
		@Override
		public BigDecimal prixCommandeProuit(Produit p, Conditionnement c, int quantite) {
			
			BigDecimal quantite1 = BigDecimal.valueOf(quantite);

			return prixCommandeProuit(p, c, quantite1);
		}
		
		
		public BigDecimal prixCommandeProuit(Produit p, Conditionnement c, BigDecimal quantite) {// cas des commandes avec quantité avec décimales autorisée (0.5 litre,3.5 kilogrammes...)
			BigDecimal prixCommandeProduit = new BigDecimal(0);

			BigDecimal pu = prixUnitaire(p, c); // on déduit le prix unitaire


			prixCommandeProduit = pu.multiply(quantite);
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

	
	// Quantité déstockée suite à l'achat d'un produit (il s'agit ici de tenir
		// compte des bonifications éventuelles)
		public int getQuantiteDestockee(Produit p, Conditionnement c, int quantite) {

			int qteDestockee = 0;
			
			Optional<ConditionnementProduit> isNull = Optional.ofNullable(tarifProduit(p.getId(), c.getId())); 
				if ( isNull.isPresent()) {
			int qtebonus = tarifProduit(p.getId(), c.getId()).getQuantitePourBonus();
			int qteBonifiee = tarifProduit(p.getId(), c.getId()).getQuantiteBonifiee();

			if (qtebonus > 0) {
				qteDestockee = quantite + (quantite / qtebonus) * qteBonifiee;
			} else {
				qteDestockee = quantite;
			}
			}

			return qteDestockee;

		}

		// Indiquer la quantité de bonus donnée pour un commande
		public int getQuantiteTotalBonus(Produit p, Conditionnement c, BigDecimal quantite) {

			int qteTotaleBonus = 0;
			int qteEntiere = quantite.intValue();

				Optional<ConditionnementProduit> isNull = Optional.ofNullable(tarifProduit(p.getId(), c.getId())); 
				if ( isNull.isPresent()) {
			int qtebonus = tarifProduit(p.getId(), c.getId()).getQuantitePourBonus();
			int qteBonifiee = tarifProduit(p.getId(), c.getId()).getQuantiteBonifiee();
			if (qtebonus > 0) {
				qteTotaleBonus = qteEntiere / qtebonus * qteBonifiee;
			}
			}
			return qteTotaleBonus;
		}

}
