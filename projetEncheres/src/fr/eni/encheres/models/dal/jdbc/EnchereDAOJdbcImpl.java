package fr.eni.encheres.models.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.eni.encheres.models.bo.Categorie;
import fr.eni.encheres.models.bo.Enchere;
import fr.eni.encheres.models.bo.Utilisateur;
import fr.eni.encheres.models.dal.EnchereDAO;
import fr.eni.encheres.models.dal.exception.DALException;

public class EnchereDAOJdbcImpl extends Exception implements EnchereDAO {

	ResourceBundle languages = ResourceBundle.getBundle("fr.eni.languages.language");
	
	private static final String SELECT_ENCHERES = "SELECT * FROM ENCHERES e "
			+ "INNER JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article "
			+ "INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur ";
	private static final String SELECT_MES_ENCHERES = "SELECT * FROM ENCHERES e "
			+ "INNER JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article "
			+ "INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur "
			+ "WHERE no_utilisateur = ?";
	private static final String SELECT_ENCHERES_FILTRE = "SELECT * FROM ENCHERES e "
			+ "INNER JOIN ARTICLES_VENDUS a ON e.no_article = a.no_article "
			+ "INNER JOIN UTILISATEURS u ON e.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
			+ "WHERE  c.libelle = ?";
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
				enchere.setIdCategorie(rs.getInt("no_categorie"));
				enchere.setDate_enchere(rs.getDate("date_enchere"));
				enchere.setMontant(rs.getInt("montant_enchere"));
				enchere.setNomArticle(rs.getString("nom_article"));
				enchere.setDescription(rs.getString("description"));
				enchere.setDateDebutEnchere(rs.getDate("date_debut_encheres"));
				enchere.setDateFinEnchere(rs.getDate("date_fin_encheres"));
				enchere.setPrixInitial(rs.getInt("prix_initial"));
				enchere.setPrixFinal(rs.getInt("prix_vente"));
				Utilisateur u = new Utilisateur();
				u.setPseudo(rs.getString("pseudo"));
				enchere.setUtilisateur(u);
				listeE.add(enchere);
			}
		} catch (SQLException e) {
			throw new DALException(languages.getString("getEnchereERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
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
					throw new DALException(languages.getString("getUtilisateurEncheresERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException(languages.getString("noData"));
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
				pstmt.setDate(3, java.sql.Date.valueOf(enchere.getDateEnchere().toString()));
				pstmt.setInt(4, enchere.getMontant());
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				throw new DALException(languages.getString("addEnchereERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException(languages.getString("noData"));
		}
	}

	@Override
	public void updateEnchere(Enchere enchere) throws DALException, SQLException {
		Connection cnx = null;
		
		if (enchere != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ENCHERE);
				pstmt.setDate(1, java.sql.Date.valueOf(enchere.getDateEnchere().toString()));
				pstmt.setInt(2, enchere.getMontant());
				pstmt.setInt(3,  enchere.getIdUtilisateur());
				pstmt.setInt(4,  enchere.getIdArticle());
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				throw new DALException(languages.getString("updateEnchereERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException(languages.getString("noData"));
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
			throw new DALException(languages.getString("getCategoriesERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
		}
		return listeC;
	}

	@Override
	public List<Enchere> getEncheres(String nomCat) throws DALException {
		List<Enchere> listeE = new ArrayList<Enchere>();
		if (nomCat != null)
		{
			try {
				Enchere enchere = null;
				Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERES_FILTRE);
				pstmt.setString(1, nomCat);
				pstmt.executeQuery();
				ResultSet rs = pstmt.getResultSet();
				while(rs.next()) {
					enchere = new Enchere();
					enchere.setIdUtilisateur(rs.getInt("no_utilisateur"));
					enchere.setIdArticle(rs.getInt("no_article"));
					enchere.setIdCategorie(rs.getInt("no_categorie"));
					enchere.setDate_enchere(rs.getDate("date_enchere"));
					enchere.setMontant(rs.getInt("montant_enchere"));
					enchere.setNomArticle(rs.getString("nom_article"));
					enchere.setDescription(rs.getString("description"));
					enchere.setDateDebutEnchere(rs.getDate("date_debut_encheres"));
					enchere.setDateFinEnchere(rs.getDate("date_fin_encheres"));
					enchere.setPrixInitial(rs.getInt("prix_initial"));
					enchere.setPrixFinal(rs.getInt("prix_vente"));
					Utilisateur u = new Utilisateur();
					u.setPseudo(rs.getString("pseudo"));
					enchere.setUtilisateur(u);
					listeE.add(enchere);
				}
			} catch (SQLException e) {
				throw new DALException(languages.getString("getEnchereERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
			}
		}
		return listeE;
	}
}