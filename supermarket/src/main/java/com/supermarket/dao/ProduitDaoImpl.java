package com.supermarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.model.Produit;

public class ProduitDaoImpl implements ProduitDao {

	private static final String INSERT_PRODUIT_SQL = "INSERT INTO produit" + "  (nom_produit) VALUES " + " (?);";

	private static final String SELECT_PRODUIT_BY_ID = "select id,nom_produit from produit where id =?";
	private static final String SELECT_ALL_PRODUITS = "select * from produit";
	private static final String DELETE_PRODUITS_SQL = "delete from produit where id = ?;";
	private static final String UPDATE_PRODUITS_SQL = "update produit set nom_produit = ? where id = ?;";
	private static final String SELECT_NB_PRODUIT = "select count(*) from produit;";

	
	public void insertProduit(Produit produit) {
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_PRODUIT_SQL)) {

			preparedStatement.setString(1, produit.getNomProduit());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Produit findProduit(long id) {

		Produit produit = null;

		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_PRODUIT_BY_ID);) {

			preparedStatement.setLong(1, id);

			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String nomProduit = rs.getString(2);

				produit = new Produit(id, nomProduit);
			}
		} catch (SQLException e) {e.printStackTrace();
		}

		return produit;
	}

	public long nombreProduit() {
		long nb = -1;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_NB_PRODUIT);) {

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				nb = rs.getLong(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nb;
	}

	public List<Produit> selectAllProduit() {
		List<Produit> produits = new ArrayList<>();

		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_PRODUITS);) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				long id = rs.getLong(1);
				String nomProduit = rs.getString(2);
				produits.add(new Produit(id, nomProduit));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return produits;
	}

	public boolean deleteProduit(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateProduit(Produit produit) {
		boolean result = false;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement statement = con.prepareStatement(UPDATE_PRODUITS_SQL);) {

			statement.setString(1, produit.getNomProduit());
			statement.setLong(2, produit.getId());
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
