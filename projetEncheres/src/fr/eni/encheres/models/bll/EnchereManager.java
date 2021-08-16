package fr.eni.encheres.models.bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.models.bll.exceptions.BLLException;
import fr.eni.encheres.models.bo.Categorie;
import fr.eni.encheres.models.bo.Enchere;
import fr.eni.encheres.models.dal.EnchereDAO;
import fr.eni.encheres.models.dal.exception.DALException;
import fr.eni.encheres.models.dal.jdbc.EnchereDAOJdbcImpl;

public class EnchereManager {

	private EnchereDAO enchereDAO;
	
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
		}
		return listeE;
	}
	
	public List<Enchere> getEncheres(String nomCategorie) throws BLLException {
		BLLException exceptions = new BLLException();
		
		List<Enchere> listeE = new ArrayList<Enchere>();
		
		try {
			listeE = enchereDAO.getEncheres(nomCategorie);
		} catch (DALException e) {
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
		}
		return listeC;
	}
	
	public void ajouterEnchere(Enchere enchere) throws BLLException {
		BLLException exceptions = new BLLException();
		
		try {
			enchereDAO.insertEnchere(enchere);
		} catch (DALException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		} catch (SQLException e) {
			exceptions.ajoutErreur(e.getMessage());
			throw exceptions;
		}
	}
	
}
