package fr.eni.encheres.models.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.models.bo.Categorie;
import fr.eni.encheres.models.bo.Enchere;
import fr.eni.encheres.models.dal.EnchereDAO;
import fr.eni.encheres.models.dal.exception.DALException;

public class EnchereDAOJdbcImpl extends Exception implements EnchereDAO {

	private static final String SELECT_ENCHERES = "SELECT * FROM ENCHERES";
	private static final String SELECT_MES_ENCHERES = "SELECT * FROM ENCHERES WHERE no_utilisateur = ?";
	private static final String SELECT_CATEGORIES = "SELECT * FROM CATEGORIES";
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERE(no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?)";
	private static final String UPDATE_ENCHERE = "UPDATE ENCHERE SET date_enchere = ?, montant_enchere = ? WHERE no_utilisateur = ? AND no_article = ? ";
	
	@Override
	public List<Enchere> getEncheres() throws DALException {
		List<Enchere> listeE = new ArrayList<Enchere>();
		try {
			Enchere enchere = null;
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ENCHERES);
			while(rs.next()) {
				enchere = new Enchere();
				enchere.setIdUtilisateur(rs.getInt("no_utilisateur"));
				enchere.setIdArticle(rs.getInt("no_article"));
				enchere.setDate_enchere(rs.getDate("date_enchere"));
				enchere.setMontant(rs.getInt("montant_enchere"));
				listeE.add(enchere);
			}
		} catch (SQLException e) {
			throw new DALException("Problème lors de la récupération des enchères. "
					+ "Contactez votre service informatique [" + e.getMessage() + "]");
		}
		return listeE;
	}

	@Override
	public List<Enchere> getUserEncheres(int idUser) throws DALException {
		List<Enchere> listeE = new ArrayList<Enchere>();
		if (idUser != 0)
		{
			try {
				Enchere enchere = null;
				Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_MES_ENCHERES);
				pstmt.setInt(1, idUser);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getResultSet();
				while(rs.next()) {
					enchere = new Enchere();
					enchere.setIdUtilisateur(rs.getInt("no_utilisateur"));
					enchere.setIdArticle(rs.getInt("no_article"));
					enchere.setDate_enchere(rs.getDate("date_enchere"));
					enchere.setMontant(rs.getInt("montant_enchere"));
					listeE.add(enchere);
				}
			} catch (SQLException e) {
					throw new DALException("Problème lors de la récupération des enchères. "
							+ "Contactez votre service informatique [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException("Aucun utilisateur ciblé");
		}
		return listeE;
	}

	
	@Override
	public void insertEnchere(Enchere enchere) throws DALException, SQLException {
		Connection cnx = null;
		
		if (enchere != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE);
				pstmt.setInt(1,  enchere.getIdUtilisateur());
				pstmt.setInt(2,  enchere.getIdArticle());
				pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDate_enchere().toString()));
				pstmt.setInt(4, enchere.getMontant());
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				throw new DALException("Problème lors de l'ajout de l'enchère. "
						+ "Contactez votre service informatique [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException("Aucune données saisie");
		}
	}

	@Override
	public void updateEnchere(Enchere enchere) throws DALException, SQLException {
		Connection cnx = null;
		
		if (enchere != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
				pstmt.setDate(1, java.sql.Date.valueOf(enchere.getDate_enchere().toString()));
				pstmt.setInt(2, enchere.getMontant());
				pstmt.setInt(3,  enchere.getIdUtilisateur());
				pstmt.setInt(4,  enchere.getIdArticle());
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				throw new DALException("Problème lors de l'ajout de l'enchère. "
						+ "Contactez votre service informatique [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException("Aucune données saisie");
		}
	}

	@Override
	public List<Categorie> getCategories() throws DALException {
		List<Categorie> listeC = new ArrayList<Categorie>();
		try {
			Categorie categorie = null;
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_CATEGORIES);
			while(rs.next()) {
				categorie = new Categorie();
				categorie.setIdCategorie(rs.getInt("no_categorie"));
				categorie.setLibelle(rs.getString("libelle"));
				listeC.add(categorie);
			}
		} catch (SQLException e) {
			throw new DALException("Problème lors de la récupération des catégories d'enchères. "
					+ "Contactez votre service informatique [" + e.getMessage() + "]");
		}
		return listeC;
	}
	
	

}
