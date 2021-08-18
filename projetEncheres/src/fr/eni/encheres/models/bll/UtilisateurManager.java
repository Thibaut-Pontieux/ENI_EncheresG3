package fr.eni.encheres.models.bll;

import java.sql.SQLException;

import fr.eni.encheres.models.bll.exceptions.BLLException;
import fr.eni.encheres.models.bo.Utilisateur;
import fr.eni.encheres.models.dal.UtilisateurDAO;
import fr.eni.encheres.models.dal.exception.DALException;
import fr.eni.encheres.models.dal.jdbc.UtilisateurDAOJdbcImpl;

public class UtilisateurManager {
	
	private UtilisateurDAO utilisateurDAO;

	public UtilisateurManager() {
		utilisateurDAO = new UtilisateurDAOJdbcImpl();
	}
	
	public void ajouterUtilisateur(String nom, String prenom, String pseudo, String email, String mdp, String phone,
			String street, String postalcode, String city) throws BLLException {
		BLLException exceptions = new BLLException();
		
		Utilisateur utilisateur = new Utilisateur();
		
		try {
			utilisateurDAO.insertUtilisateur(utilisateur);
		} catch (DALException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		} catch (SQLException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		}
	}
	
}
