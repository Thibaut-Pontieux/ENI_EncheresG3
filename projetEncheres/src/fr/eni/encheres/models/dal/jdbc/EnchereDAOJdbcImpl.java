package fr.eni.encheres.models.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.models.bo.Enchere;
import fr.eni.encheres.models.dal.EnchereDAO;
import fr.eni.encheres.models.dal.exception.DALException;

public class EnchereDAOJdbcImpl extends Exception implements EnchereDAO {

	private static final String SELECT_ENCHERES = "SELECT * FROM ENCHERES";
	
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

}
