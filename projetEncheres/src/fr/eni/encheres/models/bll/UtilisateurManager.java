package fr.eni.encheres.models.bll;

import java.sql.SQLException;
import java.util.regex.Pattern;

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
	
	public void ajouterUtilisateur(String nom, String prenom, String pseudo, String email, String mdp, String telephone,
			String rue, String codePostal, String ville) throws BLLException {
		BLLException exceptions = new BLLException();
		
		 String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 String phoneregex = "/^((\\+|00)33\\s?|0)[1-59](\\s?\\d{2}){4}$/";
		 String postalcoderegex = "/^0[1-9]|[1-8][0-9]|9[0-8]|2A|2B[0-9]{3}$/";
		 
		 Pattern pattern = Pattern.compile(regex);
		 Pattern phonepattern = Pattern.compile(phoneregex);
		 Pattern postalcodepattern = Pattern.compile(postalcoderegex);
		 /*
		 if (pattern.matcher(email).matches() == false) {
			 exceptions.ajoutErreur("Le format de l'email est incorrect");
		 }
		 
		 if (phonepattern.matcher(telephone).matches() == false) {
			 exceptions.ajoutErreur("Le format du téléphone est incorrect");
		 }
		 
		 if (postalcodepattern.matcher(codePostal).matches() == false) {
			 exceptions.ajoutErreur("Le format du code postal est incorrect");
		 }
		*/ 
		 if (exceptions.hasError()) throw exceptions;
		
		try {
			Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
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
