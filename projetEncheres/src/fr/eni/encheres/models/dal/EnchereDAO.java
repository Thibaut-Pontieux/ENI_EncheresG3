package fr.eni.encheres.models.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.models.bo.Categorie;
import fr.eni.encheres.models.bo.Enchere;
import fr.eni.encheres.models.dal.exception.DALException;

public interface EnchereDAO {
	List<Enchere> getEncheres() throws DALException;
	List<Enchere> getEncheres(String nomCat) throws DALException;
	List<Enchere> getUserEncheres(int idUser) throws DALException;
	List<Categorie> getCategories() throws DALException;
	void insertEnchere(Enchere enchere) throws DALException, SQLException;
	void updateEnchere(Enchere enchere) throws DALException, SQLException;
}
