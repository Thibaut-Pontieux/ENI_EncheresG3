package fr.eni.encheres.models.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.models.bo.Categorie;
import fr.eni.encheres.models.bo.Enchere;
import fr.eni.encheres.models.dal.exception.DALException;

public interface EnchereDAO {
	List<Enchere> getEncheres() throws DALException, SQLException;
	List<Enchere> getEncheres(String nomCat, String search) throws DALException, SQLException;
	List<Enchere> getEncheres(int idUser, String nomCat, String search) throws DALException, SQLException;
	List<Enchere> getUserEncheres(int idUser) throws DALException, SQLException;
	List<Categorie> getCategories() throws DALException, SQLException;
	void insertNouvelArticle(Enchere enchere) throws DALException, SQLException;
	void insertEnchere(Enchere enchere) throws DALException, SQLException;
	void updateEnchere(Enchere enchere) throws DALException, SQLException;
}
