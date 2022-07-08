package com.supermarket.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.supermarket.metier.ProduitMetier;
import com.supermarket.metier.ProduitMetierImpl;
import com.supermarket.model.Produit;

public class ProduitTest {

	private ProduitMetier produitMetier;

	public ProduitTest() {
		super();
		this.produitMetier = new ProduitMetierImpl();

	}

	@Test
	public void SelectProduitByIdtest() {
		// fail("Not yet implemented");

		Produit actual = this.produitMetier.findById(1);

		Assert.assertEquals(actual.getNomProduit(), "Riz");

	}

	@Test
	public void test() {
		// fail("Not yet implemented");
		Produit expected = new Produit("Riz Sen1");
		long nb_avant = produitMetier.nombreProduit();
		produitMetier.saveProduit(expected);
		long nb_apres = produitMetier.nombreProduit();

		Assert.assertEquals(nb_avant + 1, nb_apres);

	}
}
