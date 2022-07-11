package com.supermarket.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.supermarket.metier.ConditionnementMetier;
import com.supermarket.metier.ConditionnementMetierImpl;
import com.supermarket.model.Conditionnement;

public class ConditionnementTest {
	
   private ConditionnementMetier conditionnementMetier;

	

	public ConditionnementTest() {
		super();
		conditionnementMetier = new ConditionnementMetierImpl(); 
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*	@Test
	public void test() {
		Conditionnement expected = new Conditionnement("Kilogramme");
		long nb_avant = conditionnementMetier.nombreConditionnement();
		conditionnementMetier.saveConditionnement(expected);
		long nb_apres = conditionnementMetier.nombreConditionnement();

		Assert.assertEquals(nb_avant + 1, nb_apres);
	}*/
	
	@Test
	public void SelectCondionnementByIdtest() {
		// fail("Not yet implemented");

		Conditionnement actual = this.conditionnementMetier.findById(1);

		Assert.assertEquals(actual.getNomConditionnement(), "Litre");

	}
	
	@Test
	public void UpdateConditionnementTest() {
		// fail("Not yet implemented");
		boolean result = false;
		Conditionnement expected = new Conditionnement(1, "Litre");
		result = conditionnementMetier.modifierConditionnement(expected);
		Assert.assertTrue(result);
		// Assert.
		// Assert.assertEquals(nb_avant + 1, nb_apres);

	}
}
