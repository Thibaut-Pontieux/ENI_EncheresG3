package fr.eni.encheres.models.dal;

import java.util.List;

import fr.eni.encheres.models.bo.Enchere;
import fr.eni.encheres.models.dal.exception.DALException;

public interface EnchereDAO {
	List<Enchere> getEncheres() throws DALException;
}
