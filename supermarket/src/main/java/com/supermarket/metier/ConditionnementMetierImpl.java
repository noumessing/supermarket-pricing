package com.supermarket.metier;

import java.util.List;

import com.supermarket.dao.ConditionnementDao;
import com.supermarket.dao.ConditionnementDaoImpl;
import com.supermarket.model.Conditionnement;

public class ConditionnementMetierImpl implements ConditionnementMetier {
	
private ConditionnementDao conditionnementDao;
	
	
	

	public ConditionnementMetierImpl() {
		super();
		this.conditionnementDao = new ConditionnementDaoImpl();
	}

	public ConditionnementMetierImpl(ConditionnementDao conditionnementDao) {

		this.conditionnementDao = conditionnementDao;
	}

	@Override
	public void saveConditionnement(Conditionnement c) {
		conditionnementDao.insertConditionnement(c);
	
	}

	@Override
	public List<Conditionnement> listConditionnement() {

		return conditionnementDao.selectAllConditionnement();
	}

	@Override
	public Conditionnement findById(long id) {

		return conditionnementDao.findConditionnement(id);
	}

	@Override
	public long nombreConditionnement() {
		
		return conditionnementDao.nombreConditionnement();
	}

	@Override
	public boolean modifierConditionnement(Conditionnement c) {
		
		return conditionnementDao.updateConditionnement(c);
	}


}
