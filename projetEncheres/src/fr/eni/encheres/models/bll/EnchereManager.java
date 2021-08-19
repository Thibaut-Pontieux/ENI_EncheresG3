package fr.eni.encheres.models.bll;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.eni.encheres.models.bll.exceptions.BLLException;
import fr.eni.encheres.models.bo.Categorie;
import fr.eni.encheres.models.bo.Enchere;
import fr.eni.encheres.models.dal.EnchereDAO;
import fr.eni.encheres.models.dal.exception.DALException;
import fr.eni.encheres.models.dal.jdbc.EnchereDAOJdbcImpl;

public class EnchereManager {

	private EnchereDAO enchereDAO;
	
	ResourceBundle languages = ResourceBundle.getBundle("fr.eni.languages.language");
	
	public EnchereManager() {
		enchereDAO = new EnchereDAOJdbcImpl();
	}
	
	public List<Enchere> getEncheres() throws BLLException {
		BLLException exceptions = new BLLException();
		
		List<Enchere> listeE = new ArrayList<Enchere>();
		
		try {
			listeE = enchereDAO.getEncheres();
		} catch (DALException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		} catch (SQLException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		}
		return listeE;
	}
	
	public List<Enchere> getEncheres(String nomCategorie, String search) throws BLLException {
		BLLException exceptions = new BLLException();
		
		List<Enchere> listeE = new ArrayList<Enchere>();
		
		try {
			listeE = enchereDAO.getEncheres(nomCategorie, search);
		} catch (DALException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		} catch (SQLException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		}
		return listeE;
	}
	
	public List<Enchere> getEncheres(int idUtilisateur, String nomCategorie, String search) throws BLLException {
		BLLException exceptions = new BLLException();
		
		List<Enchere> listeE = new ArrayList<Enchere>();
		
		try {
			listeE = enchereDAO.getEncheres(idUtilisateur, nomCategorie, search);
		} catch (DALException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		} catch (SQLException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		}
		return listeE;
	}
	
	public List<Enchere> getMesEncheres(int idUtilisateur) throws BLLException {
		BLLException exceptions = new BLLException();
		
		List<Enchere> listeE = new ArrayList<Enchere>();
		if(idUtilisateur == 0) {
			exceptions.ajoutErreur("Erreur, utilisateur inconnu");
			throw exceptions;
		}
		try {
			listeE = enchereDAO.getUserEncheres(idUtilisateur);
		} catch (DALException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		} catch (SQLException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		}
		return listeE;
	}
	
	public List<Categorie> getCategories() throws BLLException {
		BLLException exceptions = new BLLException();
		
		List<Categorie> listeC = new ArrayList<Categorie>();
		
		try {
			listeC = enchereDAO.getCategories();
		} catch (DALException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		} catch (SQLException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		}
		return listeC;
	}
	
	public void ajouterArticle(int numCat, String nomArt, String descArt, int prixArt, String dateDebVente, String dateFinVente, String heureDebVente, String heureFinVente) throws BLLException {
		BLLException exceptions = new BLLException();
		LocalDate dateDeb = null;
		LocalDate dateFin = null;
		LocalTime heureDeb = null;
		LocalTime heureFin = null;
		
		/*	Série de vérifications pour insertion d'un nouvel article en base
		 * 
		 * Premières vérifications --> Format date / heure
		 */
		try {
			dateDeb = validerFormatDate(dateDebVente);
			dateFin = validerFormatDate(dateDebVente);
		} catch (Exception e) {
			exceptions.ajoutErreur(e.getMessage());
		}
		
		try {
			heureDeb = validerFormatHeure(heureDebVente);
			heureFin = validerFormatHeure(heureFinVente);
		} catch (Exception e) {
			exceptions.ajoutErreur(e.getMessage());
		}
		
		// Vérification concordance des dates
		try {
			validerDate(dateDeb);
			validerDate(dateFin);
		} catch (Exception e) {
			exceptions.ajoutErreur(e.getMessage());
		}
		
		if (nomArt.length() > 30) exceptions.ajoutErreur(languages.getString("tailleNomArticle"));
		if (descArt.length() > 300) exceptions.ajoutErreur("La description de l'article ne doit pas dépasser 300 caractères");
		if (prixArt > 1000000000) exceptions.ajoutErreur("Le prix de l'article ne doit pas dépasser 1 milliard de crédits");
		if (numCat == 0) exceptions.ajoutErreur("La catégorie est obligatoire");
		//if (numUtilisateur == 0) exceptions.ajoutErreur("Aucun utilisateur n'a été trouvé veuillez vous reconnecter et reessayer");
		if (nomArt == "") exceptions.ajoutErreur("Le nom de l'article est obligatoire");
		if (descArt == "") exceptions.ajoutErreur("La description est obligatoire");
		if (prixArt == 0) exceptions.ajoutErreur("Le prix est obligatoire et doit être supérieur à 0 crédit");
		if (dateDeb == null) exceptions.ajoutErreur("L'heure de début est obligatoire");
		if (dateFin == null) exceptions.ajoutErreur("L'heure de fin est obligatoire");
		
		if (exceptions.hasError()) throw exceptions;
			
		try {
			Enchere enchere = new Enchere(1, numCat, nomArt, descArt, prixArt, dateDeb, dateFin);
			enchereDAO.insertNouvelArticle(enchere);
		} catch (DALException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		} catch (SQLException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		}
	}
	
	private LocalDate validerFormatDate(String date) throws Exception {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateFormat = LocalDate.parse(date, dtf);
			return dateFormat;
		} catch (RuntimeException e) {
			throw new RuntimeException("Le format de la date est incorrect");
		}
	}
	
	private LocalTime validerFormatHeure(String heure) throws Exception {
		try {
			DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm");
			LocalTime heureFormat = LocalTime.parse(heure, tf);
			return heureFormat;
		} catch (RuntimeException e) {
			throw new RuntimeException("Le format de l'heure est incorrect");
		}
	}
	
	private void validerDate(LocalDate date) throws Exception {
		if(date.isBefore(LocalDate.now())) {
			Exception e = new Exception("Date incorrecte, la mise aux enchères ne peut être effectuée qu'à partir du " 
					+ String.valueOf(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
			throw e;
		}
	}
}
