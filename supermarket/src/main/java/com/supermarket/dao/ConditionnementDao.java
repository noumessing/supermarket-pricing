package com.supermarket.dao;

import java.util.List;

import com.supermarket.model.Conditionnement;

public interface ConditionnementDao {

	void insertConditionnement(Conditionnement conditionnement);

	Conditionnement findConditionnement(long id);

	List<Conditionnement> selectAllConditionnement();

	boolean deleteConditionnement(long id);

	boolean updateConditionnement(Conditionnement conditionnement);

	long nombreConditionnement();

}
