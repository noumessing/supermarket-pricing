package com.supermarket.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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

	/*
	 * @Test public void test() { // test ajout d'un produit
	 *  Produit expected =
	 * new Produit("Riz Sen1"); long nb_avant = produitMetier.nombreProduit();
	 * produitMetier.saveProduit(expected); long nb_apres =
	 * produitMetier.nombreProduit();
	 * 
	 * Assert.assertEquals(nb_avant + 1, nb_apres);
	 * 
	 * }
	 */

	@Test
	public void UpdateProduittest() {
		// fail("Not yet implemented");
		boolean result = false;
		Produit expected = new Produit(13, "Riz Sen3");
		result = produitMetier.modifierProduit(expected);
		Assert.assertTrue(result);
		// Assert.
		// Assert.assertEquals(nb_avant + 1, nb_apres);

	}

	@Test
	public void ListProduittest() {
		// long nbProduit = produitMetier.nombreProduit();

		List<Produit> produitsAttendus = Arrays.asList(new Produit(1, "Riz"), new Produit(2, "Riz blanc"),
				new Produit(3, "Riz jaune"), new Produit(4, "Riz Sen"), new Produit(5, "Riz Sen1"),
				new Produit(9, "Riz Sen2"), new Produit(10, "Riz Sen1"), new Produit(11, "Riz Sen1"),
				new Produit(12, "Riz Sen1"), new Produit(13, "Riz Sen3")); // liste des produits en bd

		List<Produit> produitsActual = produitMetier.listProduit();

		/*
		 * long longeurListe=produitsActual.size(); Assert.assertEquals(nbProduit,
		 * longeurListe);
		 */

		// Assert.assertEquals(produitsAttendus, produitsActual);
		assertTrue(produitsAttendus.size() == produitsActual.size() && produitsAttendus.containsAll(produitsActual)
				&& produitsActual.containsAll(produitsAttendus));

	}
}
