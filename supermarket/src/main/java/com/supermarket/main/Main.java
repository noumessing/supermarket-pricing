package com.supermarket.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.supermarket.metier.CondProduitMetierImpl;
import com.supermarket.metier.ProduitMetierImpl;
import com.supermarket.model.Commande;
import com.supermarket.model.Conditionnement;
import com.supermarket.model.Produit;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	ProduitDao1 p = new ProduitDao1();
	//	Produit p1 = new Produit();
	//	p.saveProduit(p1);
		
	//	System.out.println(new Date());
		

		 ProduitMetierImpl produitMetier = new ProduitMetierImpl();
		 Produit expected = new Produit("Riz Sen2");
			//produitMetier.saveProduit(expected);
			
			
			produitMetier.saveProduit(expected);
			long id = expected.getId();
			
			System.out.println(id);
			
			Produit actual =produitMetier.findById(id);
			
			CondProduitMetierImpl condProduitMetier = new CondProduitMetierImpl();
			
			
			Produit riz = new Produit(1, "Riz");
			Conditionnement kilo = new Conditionnement(2, "kilogramme");
			Produit pomme = new Produit(5, "Pomme");
			Conditionnement boite = new Conditionnement(7, "Boite");
			Produit beer = new Produit(9, "Bierre");
			Conditionnement btle = new Conditionnement(5, "Bouteille");
			
			Produit ygurt = new Produit(14, "Yaourt 400gr");
			Conditionnement pot = new Conditionnement(3, "Pot");
			
			Produit cir = new Produit(4, "Cirage");
			Conditionnement bte = new Conditionnement(7, "Boite");
			
			List<Commande> commandes = new ArrayList<Commande>();
			
			commandes.add(new Commande(riz, kilo, BigDecimal.valueOf(5.5)));
			commandes.add(new Commande(pomme, kilo, BigDecimal.valueOf(5.25)));
			commandes.add(new Commande(beer, btle, BigDecimal.valueOf(24)));
			commandes.add(new Commande(ygurt, pot, BigDecimal.valueOf(17)));
			commandes.add(new Commande(cir, bte, BigDecimal.valueOf(8)));
			

			
			commandes.forEach(cmd ->{
				System.out.println("la commande de "+ cmd.getQuatite() +" " + cmd.getConditionnement().getNomConditionnement() + " de " + cmd.getProduit().getNomProduit() +
						" vaut " + condProduitMetier.prixCommandeProuit(cmd.getProduit(), cmd.getConditionnement(), cmd.getQuatite()) );
				
				System.out.println( "Cette commande doit droit à "+ condProduitMetier.getQuantiteTotalBonus(cmd.getProduit(), cmd.getConditionnement(), cmd.getQuatite()) +
						" "+ cmd.getConditionnement().getNomConditionnement() + " de bonus");
				
			});
			
			
			BigDecimal sum = commandes.stream().map(cmd ->condProduitMetier.prixCommandeProuit(cmd.getProduit(), cmd.getConditionnement(), cmd.getQuatite())).reduce(BigDecimal.ZERO, BigDecimal::add);
			
			 
			
			System.out.println("Le montant total des commandes est :"+ sum );
			

			
	}

}
