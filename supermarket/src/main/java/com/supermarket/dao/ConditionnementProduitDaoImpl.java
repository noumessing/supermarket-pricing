package com.supermarket.dao;

import java.math.BigDecimal;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.supermarket.model.Conditionnement;
import com.supermarket.model.ConditionnementProduit;
import com.supermarket.model.Produit;

public class ConditionnementProduitDaoImpl implements ConditionnementProduitDao {

	private final ConditionnementDao conditionnementDao;
	private final ProduitDao produitDao;

	public ConditionnementProduitDaoImpl() {
		super();
		conditionnementDao = new ConditionnementDaoImpl();
		produitDao = new ProduitDaoImpl();
	}

	private static final String INSERT_CONDITIONNEMENT_PRODUIT_SQL = "INSERT INTO conditionnementproduit"
			+ "  (idp,idc,quantite,prix,date_effective,quantitepourbonus,quantitebonifiee) VALUES "
			+ " (?,?,?,?,?,?,?);";

	private static final String SELECT_CONDITIONNEMENT_PRODUIT_BY_IDS = "select idcp ,quantite,prix,date_effective,quantitepourbonus,quantitebonifiee from conditionnementproduit where idp =? and idc =?;";
	private static final String SELECT_ALL_CONDITIONNEMENTS = "select * from conditionnementproduit";
	private static final String SELECT_CONDITIONNEMENTS_PRODUITS_PAR_ID = "select idp,idc,quantite,prix,date_effective,quantitepourbonus,quantitebonifiee from conditionnementproduit where idcp = ?;";
	private static final String SELECT_CONDITIONNEMENTS_PAR_PRODUIT = "select idcp, idc,quantite,prix,date_effective,quantitepourbonus,quantitebonifiee from conditionnementproduit where idp = ?;";
	private static final String DELETE_CONDITIONNEMENT_PRODUIT_SQL = "delete from conditionnementproduit where idcp = ?;";
	private static final String UPDATE_CONDITIONNEMENT_PRODUIT_SQL = "update conditionnementproduit set quantite = ?,prix=?,date_effective=?,quantitepourbonus=?,quantitebonifiee=? where idcp = ?;";
	private static final String SAVE_CONDITIONNEMENT_PRODUIT_HISTORY_SQL = "INSERT INTO condproduitsave  (idcp,quantite,prix,date_effective,date_fin_effective,quantitepourbonus,quantitebonifiee) VALUES  (?,?,?,?,?,?,?);";
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

	public ConditionnementProduit findConditionnementProduitById(long idConditionnementProduit) {

		ConditionnementProduit conditionnementProduit = null;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_CONDITIONNEMENTS_PRODUITS_PAR_ID);) {

			preparedStatement.setLong(1, idConditionnementProduit);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				long idp = rs.getLong(1);
				long idc = rs.getLong(2);
				int quantite = rs.getInt(3);
				BigDecimal prix = rs.getBigDecimal(4);
				Date dateEffet = rs.getDate(5);
				int qPourBonus = rs.getInt(6);
				int qBonifiee = rs.getInt(7);

				conditionnementProduit = new ConditionnementProduit(quantite, prix, dateEffet, qPourBonus, qBonifiee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnementProduit;

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
				long idConditionnementProduit =  rs.getLong(1);
				int quantite = rs.getInt(2);
				BigDecimal prix = rs.getBigDecimal(3);
				Date d = rs.getDate(4);
				int qpb = rs.getInt(5);
				int qb = rs.getInt(6);

				conditionnementProduit = new ConditionnementProduit(idConditionnementProduit,quantite, prix, d, qpb, qb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnementProduit;

	}

	@Override
	public List<ConditionnementProduit> selectAllConditionnementProduit() {

		List<ConditionnementProduit> conditionnementProduits = new ArrayList<>();

		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_CONDITIONNEMENTS);) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				long idcp = rs.getLong(1);
				//long IdProduit = rs.getLong(2);
				Produit prod = produitDao.findProduit(rs.getLong(2));
				//long IdConditionnement = rs.getLong(3);
				Conditionnement cond = conditionnementDao.findConditionnement(rs.getLong(3));
				int qte = rs.getInt(4);
				BigDecimal prix = rs.getBigDecimal(5);
				Date dateEffet = rs.getDate(6);
				int qteBonus = rs.getInt(7);
				int qteBonifiee = rs.getInt(8);

				conditionnementProduits.add(new ConditionnementProduit(idcp, prod, cond, qte, prix, dateEffet, qteBonus, qteBonifiee));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnementProduits;

	}

	@Override
	public boolean deleteConditionnementProduit(long id) {

		return false;
	}

	@Override
	public boolean updateConditionnementProduit(ConditionnementProduit conditionnementProduit) {

		ConditionnementProduit oldConditionnementProduit = findConditionnementProduitById(
				conditionnementProduit.getIdcp());
		boolean result = false;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement statement = con.prepareStatement(UPDATE_CONDITIONNEMENT_PRODUIT_SQL);
				PreparedStatement statementSave = con.prepareStatement(SAVE_CONDITIONNEMENT_PRODUIT_HISTORY_SQL);) {

			statement.setInt(1, conditionnementProduit.getQuantite());
			statement.setBigDecimal(2, conditionnementProduit.getPrix());
			statement.setDate(3, new java.sql.Date(conditionnementProduit.getDateEffective().getTime()));
			statement.setInt(4, conditionnementProduit.getQuantitePourBonus());
			statement.setInt(5, conditionnementProduit.getQuantiteBonifiee());
			statement.setLong(6, conditionnementProduit.getIdcp());

			// On historise l'ancienne tarification (on pouvait aussi utiliser un trigger en
			// base de données)

			Date d = new Date();
			statementSave.setLong(1, conditionnementProduit.getIdcp());
			statementSave.setInt(2, oldConditionnementProduit.getQuantite());
			statementSave.setBigDecimal(3, oldConditionnementProduit.getPrix());
			statementSave.setDate(4, new java.sql.Date(oldConditionnementProduit.getDateEffective().getTime()));
			statementSave.setDate(5, new java.sql.Date(d.getTime()));
			statementSave.setInt(6, oldConditionnementProduit.getQuantitePourBonus());
			statementSave.setInt(7, oldConditionnementProduit.getQuantiteBonifiee());
			System.out.println(statementSave);
			statementSave.executeUpdate();
			result = statement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public long nombreConditionnementProduit() {

		long nb = -1;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_NB_CONDITIONNEMENT);) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				nb = rs.getLong(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nb;

	}

	@Override
	public List<ConditionnementProduit> selectConditionnementParProduit(long idProduit) {

		Produit prod = produitDao.findProduit(idProduit);
		List<ConditionnementProduit> conditionnements = new ArrayList<ConditionnementProduit>();
		ConditionnementProduit conditionnementProduit = null;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_CONDITIONNEMENTS_PAR_PRODUIT);) {

			preparedStatement.setLong(1, idProduit);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				long idConditionnementProduit = rs.getLong(1);
				long Idconditionnement = rs.getLong(2);
				Conditionnement cond = conditionnementDao.findConditionnement(Idconditionnement);
				int quantite = rs.getInt(3);
				BigDecimal prix = rs.getBigDecimal(4);
				Date d = rs.getDate(5);
				int qpb = rs.getInt(6);
				int qb = rs.getInt(7);
				conditionnementProduit = new ConditionnementProduit(idConditionnementProduit,prod,cond, quantite, prix, d, qpb, qb);
				conditionnements.add(conditionnementProduit);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnements;
	}
}
