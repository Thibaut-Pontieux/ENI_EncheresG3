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
	
	public int getUtilisateur(String pseudo, String mdp) throws BLLException {
		BLLException exceptions = new BLLException();
		
		int ID = 0 ;
		
		try {
			ID = utilisateurDAO.utilisateurExiste(pseudo, mdp);
		} catch (DALException e) {
			exceptions.ajoutErreur("Le pseudo/email ou le mot de passe est incorrect");
			throw exceptions;
		} catch (SQLException e) {
			exceptions.ajoutErreur("Le pseudo/email ou le mot de passe est incorrect");
			throw exceptions;
		}
		return ID;
	}
	
	public  void profilUtilisateur(int  idUser) throws BLLException, DALException {
		
		utilisateurDAO.getUtilisateur(idUser);

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
		 if(nom.length() > 30) exceptions.ajoutErreur("Longueur max du nom 30 caractères !");
		 if(prenom.length() > 30) exceptions.ajoutErreur("Longueur max du prénom 30 caractères !");
		 if(pseudo.length() > 30) exceptions.ajoutErreur("Longueur max du pseudo 30 caractères !");
		 if(email.length() > 30) exceptions.ajoutErreur("Longueur max de l'email 50 caractères !");
		 if(mdp.length() > 30) exceptions.ajoutErreur("Longueur max du mot de passe 30 caractères !");
		 if(telephone.length() > 30) exceptions.ajoutErreur("Numéro de téléphone de 10 caractères max !");
		 if(rue.length() > 30) exceptions.ajoutErreur("Longueur max du nom de la rue 30 caractères !");
		 if(ville.length() > 30) exceptions.ajoutErreur("Longueur max du nom de la ville 30 caractères !");
		 if(codePostal.length() > 5) exceptions.ajoutErreur("Longueur max du code postal 5 caractères !");
		 if(nom.length() == 0 || nom.trim() == "") exceptions.ajoutErreur("Le nom est obligatoire !");
		 if(nom.length() == 0 || nom.trim() == "") exceptions.ajoutErreur("Le nom est obligatoire !");
		 if(prenom.length() == 0 || prenom.trim() == "") exceptions.ajoutErreur("Le prénom est obligatoire !");
		 if(pseudo.length() == 0 || pseudo.trim() == "") exceptions.ajoutErreur("Le pseudo est obligatoire !");
		 if(email.length() == 0 || email.trim() == "") exceptions.ajoutErreur("L'email est obligatoire !");
		 if(mdp.length() == 0 || mdp.trim() == "") exceptions.ajoutErreur("Le mot de passe est obligatoire !");
		 if(telephone.length() == 0 || telephone.trim() == "") exceptions.ajoutErreur("Le téléphone est obligatoire !");
		 if(rue.length() == 0 || rue.trim() == "") exceptions.ajoutErreur("Le nom de la rue est obligatoire !");
		 if(ville.length() == 0 || ville.trim() == "") exceptions.ajoutErreur("Le nom de la ville est obligatoire !");
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
