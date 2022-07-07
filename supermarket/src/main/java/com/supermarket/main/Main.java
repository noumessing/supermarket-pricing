package com.supermarket.main;

import java.util.Date;

import com.supermarket.metier.ProduitMetierImpl;
import com.supermarket.model.Produit;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	ProduitDao1 p = new ProduitDao1();
	//	Produit p1 = new Produit();
	//	p.saveProduit(p1);
		
		System.out.println(new Date());
		

		 ProduitMetierImpl produitMetier = new ProduitMetierImpl();
		 Produit expected = new Produit("Riz Sen2");
			//produitMetier.saveProduit(expected);
			
			
			produitMetier.saveProduit(expected);
			long id = expected.getId();
			
			System.out.println(id);
			
			Produit actual =produitMetier.findById(id);
	}

}
