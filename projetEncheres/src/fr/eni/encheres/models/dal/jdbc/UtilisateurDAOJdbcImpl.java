package fr.eni.encheres.models.dal.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.models.bo.Categorie;
import fr.eni.encheres.models.bo.Utilisateur;
import fr.eni.encheres.models.dal.UtilisateurDAO;
import fr.eni.encheres.models.dal.exception.DALException;

public class UtilisateurDAOJdbcImpl extends Exception implements UtilisateurDAO {

	private static final String SELECT_UN_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	
	@Override
	public List<Utilisateur> getListeUtilisateurs() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getUtilisateur(int idUtilisateur) throws DALException {
		Utilisateur utilisateur = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_UN_UTILISATEUR);
			while(rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMdp(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdmin(rs.getBoolean("administrateur"));
			}
		} catch (SQLException e) {
			throw new DALException("Problème lors de la récupération des catégories d'enchères. "
					+ "Contactez votre service informatique [" + e.getMessage() + "]");
		}
		return utilisateur;
	}

	@Override
	public boolean utilisateurExiste(String pseudo, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws DALException, SQLException {
		Connection cnx = null;
		/*
		if (utilisateur != null) {
			try {
				
			} catch (SQLException e) {
				throw new DALException("ProblÃ¨me lors de l'ajout de l'utilisateur. "
						+ "Contactez votre service informatique [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException("Aucune données saisie");
		}*/
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws DALException, SQLException {
		// TODO Auto-generated method stub
		
	}

}
