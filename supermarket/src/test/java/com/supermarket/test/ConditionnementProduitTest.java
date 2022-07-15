package com.supermarket.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.supermarket.metier.CondProduitMetier;
import com.supermarket.metier.CondProduitMetierImpl;
import com.supermarket.model.Conditionnement;
import com.supermarket.model.ConditionnementProduit;
import com.supermarket.model.Produit;

public class ConditionnementProduitTest {

	private CondProduitMetier condProduitMetier;

	public ConditionnementProduitTest() {
		super();
		this.condProduitMetier = new CondProduitMetierImpl();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

/*	 @Test
	public void addConditionnementProduitTest() {  //Tester l'ajout d'une tarification
		Produit p = new Produit(14, "Yaourt 400gr");
		Conditionnement c = new Conditionnement(3, "Pot");
		int quantite = 1;
		BigDecimal prix = new BigDecimal(400);
		Date dateEffet = new Date();
		int quantitePourBonus = 4;
		int quantiteBonifiee = 1;
		ConditionnementProduit expected = new ConditionnementProduit(p, c, quantite, prix, dateEffet, quantitePourBonus, quantiteBonifiee);
		long nb_avant = condProduitMetier.nombreTarification();
		condProduitMetier.addConditionnementProduit(expected);
		long nb_apres = condProduitMetier.nombreTarification();

		Assert.assertEquals(nb_avant + 1, nb_apres);
	}
	*/
	/*@Test
	public void UpdateConditionnementTest() {  //Modifier une tarification
		Produit p = new Produit(1, "Riz");
		Conditionnement c = new Conditionnement(2, "kilogramme");
		int quantite = 1;
		BigDecimal prix = new BigDecimal(400);
		boolean result = false;
		Date dateEffet = new Date();
		ConditionnementProduit expected = new ConditionnementProduit(6,p, c, quantite, prix, dateEffet, 0, 0);
		result = condProduitMetier.modifierTarifProduit(expected);
		Assert.assertTrue(result);
		
	}*/
	 
	 @Test
		public void prixCommandeProduitTest() {  //Tester l'ajout d'une tarification
			Produit p = new Produit(14, "Yaourt 400gr");
			Conditionnement c = new Conditionnement(3, "Pot");
			BigDecimal prixCommande = condProduitMetier.prixCommandeProuit( p,  c, 14);
			
			
			BigDecimal expected = new BigDecimal(4800);
			expected = expected.setScale(2,BigDecimal.ROUND_HALF_EVEN);
			Assert.assertEquals(expected, prixCommande);
		}
	 

}
