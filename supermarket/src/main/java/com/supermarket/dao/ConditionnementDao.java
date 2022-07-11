package com.supermarket.dao;

import java.util.List;

import com.supermarket.model.Conditionnement;

public interface ConditionnementDao {
	
	public void insertConditionnement(Conditionnement conditionnement);

	public Conditionnement findConditionnement(long id);

	public List<Conditionnement> selectAllConditionnement();

	public boolean deleteConditionnement(long id);

	public boolean updateConditionnement(Conditionnement conditionnement);
	
	public long nombreConditionnement();

}
