package com.supermarket.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.model.Conditionnement;

public class ConditionnementDaoImpl implements ConditionnementDao {

	private static final String INSERT_CONDITIONNEMENT_SQL = "INSERT INTO conditionnement"
			+ "  (nom_conditionnement) VALUES " + " (?);";

	private static final String SELECT_CONDITIONNEMENT_BY_ID = "select id,nom_conditionnement from conditionnement where id =?";
	private static final String SELECT_ALL_CONDITIONNEMENTS = "select * from conditionnement";
	private static final String DELETE_CONDITIONNEMENT_SQL = "delete from conditionnement where id = ?;";
	private static final String UPDATE_CONDITIONNEMENT_SQL = "update conditionnement set nom_conditionnement = ? where id = ?;";
	private static final String SELECT_NB_CONDITIONNEMENT = "select count(*) from conditionnement;";

	public void insertConditionnement(Conditionnement conditionnement) {

		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(INSERT_CONDITIONNEMENT_SQL);) {
			preparedStatement.setString(1, conditionnement.getNomConditionnement());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Conditionnement findConditionnement(long id) {

		Conditionnement conditionnement = null;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_CONDITIONNEMENT_BY_ID);) {

			preparedStatement.setLong(1, id);

			// System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String nomConditionnement = rs.getString(2);

				conditionnement = new Conditionnement(id, nomConditionnement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnement;
	}

	public List<Conditionnement> selectAllConditionnement() {
		List<Conditionnement> conditionnements = new ArrayList<>();

		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_CONDITIONNEMENTS);) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				long id = rs.getLong(1);
				String nomConditiomment = rs.getString(2);
				conditionnements.add(new Conditionnement(id, nomConditiomment));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnements;
	}

	public boolean deleteConditionnement(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateConditionnement(Conditionnement conditionnement) {

		boolean result = false;
		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement statement = con.prepareStatement(UPDATE_CONDITIONNEMENT_SQL);) {

			statement.setString(1, conditionnement.getNomConditionnement());
			statement.setLong(2, conditionnement.getId());
			result = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public long nombreConditionnement() {
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

}
