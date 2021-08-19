package fr.eni.encheres.models.dal;

import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.models.bo.Utilisateur;
import fr.eni.encheres.models.dal.exception.DALException;

public interface UtilisateurDAO {

	List<Utilisateur> getListeUtilisateurs() throws DALException;
	Utilisateur getUtilisateur(int idUtilisateur) throws DALException, SQLException;
	int utilisateurExiste(String pseudo, String mdp) throws DALException,SQLException;
	void insertUtilisateur(Utilisateur utilisateur) throws DALException, SQLException;
	void updateUtilisateur(Utilisateur utilisateur) throws DALException, SQLException;
}
