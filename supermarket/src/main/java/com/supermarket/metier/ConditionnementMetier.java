package com.supermarket.metier;

import java.util.List;

import com.supermarket.model.Conditionnement;

public interface ConditionnementMetier {

	 void saveConditionnement(Conditionnement c);

	 List<Conditionnement> listConditionnement();

	 Conditionnement findById(long id);

	 long nombreConditionnement();

	 boolean modifierConditionnement(Conditionnement c);

}
