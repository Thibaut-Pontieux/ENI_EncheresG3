package fr.eni.encheres.models.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.models.bll.exceptions.BLLException;
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
	
}
