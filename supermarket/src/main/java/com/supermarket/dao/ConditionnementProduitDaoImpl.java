package com.supermarket.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.model.Conditionnement;
import com.supermarket.model.ConditionnementProduit;

public class ConditionnementProduitDaoImpl implements ConditionnementProduitDao {
	
	private ConditionnementDao conditionnementDao;

	public ConditionnementProduitDaoImpl() {
		super();
		conditionnementDao = new ConditionnementDaoImpl();
	}

	private static final String INSERT_CONDITIONNEMENT_PRODUIT_SQL = "INSERT INTO conditionnementproduit"
			+ "  (idp,idc,quantite,prix,date_effective,quantitepourbonus,quantitebonifiee) VALUES "
			+ " (?,?,?,?,?,?,?);";

	private static final String SELECT_CONDITIONNEMENT_PRODUIT_BY_IDS = "select quantite,prix,date_effective,quantitepourbonus,quantitebonifiee from conditionnementproduit where idp =? and idc =?;";
	private static final String SELECT_ALL_CONDITIONNEMENTS = "select * from conditionnementproduit";
	private static final String SELECT_CONDITIONNEMENTS_PAR_PRODUIT = "select idc,quantite,prix,date_effective,quantitepourbonus,quantitebonifiee from conditionnementproduit where idp = ?;";
	private static final String DELETE_CONDITIONNEMENT_PRODUIT_SQL = "delete from conditionnementproduit where idcp = ?;";
	private static final String UPDATE_CONDITIONNEMENT_PRODUIT_SQL = "update conditionnementproduit set quantite = ?,prix,date_effective,quantitepourbonus,quantitebonifiee where idcp = ?;";
	private static final String SELECT_NB_CONDITIONNEMENT = "select count(*) from conditionnementproduit;";

	@Override
	public void insertConditionnementProduit(ConditionnementProduit conditionnementProduit) {

		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_CONDITIONNEMENT_PRODUIT_SQL);) {
			preparedStatement.setLong(1, conditionnementProduit.getProduit().getId());
			preparedStatement.setLong(2, conditionnementProduit.getConditionnement().getId());
			preparedStatement.setInt(3, conditionnementProduit.getQuantite());
			preparedStatement.setBigDecimal(4, conditionnementProduit.getPrix());
			preparedStatement.setDate(5, new java.sql.Date(conditionnementProduit.getDateEffective().getTime()));
			preparedStatement.setInt(6, conditionnementProduit.getQuantitePourBonus());
			preparedStatement.setInt(7, conditionnementProduit.getQuantiteBonifiee());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ConditionnementProduit findConditionnementProduit(long idProduit, long idConditionnement) {

		ConditionnementProduit conditionnementProduit = null;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_CONDITIONNEMENT_PRODUIT_BY_IDS);) {

			preparedStatement.setLong(1, idProduit);
			preparedStatement.setLong(2, idConditionnement);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {

				int quantite = rs.getInt(1);
				BigDecimal prix = rs.getBigDecimal(2);
				Date d = rs.getDate(3);
				int qpb = rs.getInt(4);
				int qb = rs.getInt(5);

				conditionnementProduit = new ConditionnementProduit(quantite, prix, d, qpb, qb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnementProduit;

	}

	@Override
	public List<ConditionnementProduit> selectAllConditionnementProduit() {

		return null;
	}

	@Override
	public boolean deleteConditionnementProduit(long id) {

		return false;
	}

	@Override
	public boolean updateConditionnementProduit(ConditionnementProduit produit) {

		return false;
	}

	@Override
	public long nombreConditionnementProduit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ConditionnementProduit> selectConditionnementParProduit(long idProduit) {

		List<ConditionnementProduit> conditionnements = new ArrayList<ConditionnementProduit>();
		ConditionnementProduit conditionnementProduit = null;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_CONDITIONNEMENTS_PAR_PRODUIT);) {

			preparedStatement.setLong(1, idProduit);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				long Idconditionnement = rs.getLong(1);
				Conditionnement cond = conditionnementDao.findConditionnement(Idconditionnement);
				int quantite = rs.getInt(2);
				BigDecimal prix = rs.getBigDecimal(3);
				Date d = rs.getDate(4);
				int qpb = rs.getInt(5);
				int qb = rs.getInt(6);
				conditionnementProduit = new ConditionnementProduit(cond, quantite, prix, d, qpb, qb);
				conditionnements.add(conditionnementProduit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnements;
	}


}
