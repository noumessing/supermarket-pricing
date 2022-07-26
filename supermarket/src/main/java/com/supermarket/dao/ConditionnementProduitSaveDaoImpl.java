package com.supermarket.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.supermarket.model.Conditionnement;
import com.supermarket.model.ConditionnementProduit;
import com.supermarket.model.ConditionnementProduitSave;
import com.supermarket.model.Produit;

public class ConditionnementProduitSaveDaoImpl implements ConditionnementProduitSaveDao{
	
	private final ConditionnementDao conditionnementDao;
	private final ProduitDao produitDao;
	private final ConditionnementProduitDao conditionnementProduitDao;

	public ConditionnementProduitSaveDaoImpl() {
		super();
		conditionnementDao = new ConditionnementDaoImpl();
		produitDao = new ProduitDaoImpl();
		conditionnementProduitDao = new ConditionnementProduitDaoImpl();
	}

	private static final String SAVE_CONDITIONNEMENT_PRODUIT_HISTORY_SQL = "INSERT INTO condproduitsave  (idcp,quantite,prix,date_effective,date_fin_effective,quantitepourbonus,quantitebonifiee) VALUES  (?,?,?,?,?,?,?);";


	private static final String SELECT_ALL_CONDITIONNEMENTS_HISTORY = "select idcps,idcp,quantite,prix,quantitepourbonus,quantitebonifiee,date_effective,date_fin_effective from condproduitsave";
	private static final String SELECT_HISTORY_CONDITIONNEMENTS_PRODUITS_PAR_ID_COND_PRODUIT = "select idcps,idcp,quantite,prix,date_effective,quantitepourbonus,quantitebonifiee,date_fin_effective from condproduitsave where idcp = ?;";
	
	private static final String SELECT_NB_CONDITIONNEMENT_SAVE = "select count(*) from condproduitsave;";

	@Override
	public void insertConditionnementProduitSave(ConditionnementProduitSave conditionnementProduitSave) {
	

	}

	@Override
	public List<ConditionnementProduitSave> findAllConditionnementProduitSave(long idProduit, long idConditionnement) {
		List<ConditionnementProduitSave> conditionnementProduitHistorys = new ArrayList<>();
		ConditionnementProduit conditionnementProduit = conditionnementProduitDao.findConditionnementProduit(idProduit, idConditionnement); 
		long idConditionnementProduit = conditionnementProduit.getIdcp();
		conditionnementProduitHistorys = selectConditionnementHistoryParCondProduit(idConditionnementProduit);

		return conditionnementProduitHistorys;
	}

	@Override
	public List<ConditionnementProduitSave> selectAllConditionnementProduitSave() {
		List<ConditionnementProduitSave> conditionnementProduitHistorys = new ArrayList<>();

		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_CONDITIONNEMENTS_HISTORY);) {

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				long idcps = rs.getLong(1);
				long idcp = rs.getLong(2);
				int qte = rs.getInt(4);
				BigDecimal prix = rs.getBigDecimal(5);
				Date dateEffet = rs.getDate(6);
				int qteBonus = rs.getInt(7);
				int qteBonifiee = rs.getInt(8);
				Date dateFinEffet = rs.getDate(8);

				ConditionnementProduit conditionnementProduit = new ConditionnementProduit(idcp, qte, prix, dateEffet,
						qteBonus, qteBonifiee);

				conditionnementProduitHistorys
						.add(new ConditionnementProduitSave(idcps, conditionnementProduit, dateFinEffet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnementProduitHistorys;
	}

	List<ConditionnementProduitSave> selectConditionnementHistoryParCondProduit(long idConditionnementProduit) {

		List<ConditionnementProduitSave> conditionnementProduitSaves = new ArrayList<ConditionnementProduitSave>();

		try (Connection con = ConnectionDao.createconnection();
				PreparedStatement preparedStatement = con
						.prepareStatement(SELECT_HISTORY_CONDITIONNEMENTS_PRODUITS_PAR_ID_COND_PRODUIT);) {

			preparedStatement.setLong(1, idConditionnementProduit);

			ResultSet rs = preparedStatement.executeQuery();


			while (rs.next()) {
				long IdconditionnementProduitSave = rs.getLong(1);

				int quantite = rs.getInt(3);
				BigDecimal prix = rs.getBigDecimal(4);
				Date dateEffet = rs.getDate(5);
				int qtePourBonus = rs.getInt(6);
				int qteBonifiee = rs.getInt(7);
				Date dateFinEffet = rs.getDate(8);
				ConditionnementProduit conditionnementProduit = new ConditionnementProduit(idConditionnementProduit,
						quantite, prix, dateEffet, qtePourBonus, qteBonifiee);
				ConditionnementProduitSave cps = new ConditionnementProduitSave(IdconditionnementProduitSave,
						conditionnementProduit, dateFinEffet);
				conditionnementProduitSaves.add(cps);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conditionnementProduitSaves;
	}

	@Override
	public List<ConditionnementProduitSave> selectConditionnementHistoryParProduit(long idProduit) {
		List<ConditionnementProduitSave> conditionnementProduitSave = new ArrayList<ConditionnementProduitSave>();
		List<ConditionnementProduit> conditionnementProduits = conditionnementProduitDao.selectConditionnementParProduit(idProduit);  // Pour trouver la liste des conditionnement de ce produit ainsi que du tarif suivant ce conditionnement 
		for (ConditionnementProduit cp : conditionnementProduits) {
			// Pour chaque conditionnement du produit connaissant son tarif on recherche l'historique des tarifiacations 
			//de ce conditionnement sous forme de liste
			long idConditionnementProduit = cp.getIdcp();
			List<ConditionnementProduitSave> listeHistoprix = selectConditionnementHistoryParCondProduit(idConditionnementProduit);
			
			for (ConditionnementProduitSave cps : listeHistoprix) {
				// Chaque historique de tarification est donc récupéré et inséré dans la liste initialisé au début de la méthode
				conditionnementProduitSave.add(cps);

			}

			System.out.println(cp);
		}
		// On retourne le résultat
		return conditionnementProduitSave;
	}

	@Override
	public long nombreConditionnementProduitSave() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ConditionnementProduitSave> findAllConditionnementProduitSave(Produit produit,
			Conditionnement conditionnement) {
		
		long idProduit =  produit.getId();
		long idConditionnement =  conditionnement.getId();
		
		return findAllConditionnementProduitSave(idProduit,idConditionnement);
	}

	@Override
	public List<ConditionnementProduitSave> selectConditionnementHistoryParProduit(Produit produit) {
		long idProduit = produit.getId();

		return selectConditionnementHistoryParProduit(idProduit);
	}

	@Override
	public List<ConditionnementProduitSave> selectConditionnementParProduit(
			ConditionnementProduit conditionnementProduit) {
		long idConditionnementProduit =  conditionnementProduit.getIdcp();

		return selectConditionnementHistoryParCondProduit(idConditionnementProduit);
	}

}
