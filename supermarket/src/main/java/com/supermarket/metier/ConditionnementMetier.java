package com.supermarket.metier;

import java.util.List;

import com.supermarket.model.Conditionnement;

public interface ConditionnementMetier {

	public void saveConditionnement(Conditionnement c);

	public List<Conditionnement> listConditionnement();

	public Conditionnement findById(long id);

	public long nombreConditionnement();

	public boolean modifierConditionnement(Conditionnement c);

}
