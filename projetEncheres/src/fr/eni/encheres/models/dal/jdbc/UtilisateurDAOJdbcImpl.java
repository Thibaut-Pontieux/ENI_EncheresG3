package fr.eni.encheres.models.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.eni.encheres.models.bo.Categorie;
import fr.eni.encheres.models.bo.Enchere;
import fr.eni.encheres.models.bo.Utilisateur;
import fr.eni.encheres.models.dal.UtilisateurDAO;
import fr.eni.encheres.models.dal.exception.DALException;

public class UtilisateurDAOJdbcImpl extends Exception implements UtilisateurDAO {

	ResourceBundle languages = ResourceBundle.getBundle("fr.eni.languages.language");
	
	private static final String SELECT_UN_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS(no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,0,false)";
	
	@Override
	public List<Utilisateur> getListeUtilisateurs() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getUtilisateur(int idUtilisateur) throws DALException {
		Utilisateur utilisateur = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_UN_UTILISATEUR);
			while(rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setIdUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCode_postal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMdp(rs.getString("mot_de_passe"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setAdmin(rs.getBoolean("administrateur"));
			}
		} catch (SQLException e) {
			throw new DALException(languages.getString("getUtilisateurERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
		}
		return utilisateur;
	}

	@Override
	public boolean utilisateurExiste(String pseudo, String mdp) throws DALException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws DALException, SQLException {
		Connection cnx = null;
		
		if (utilisateur != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_USER);
				pstmt.setInt(1,  	utilisateur.getIdUtilisateur());
				pstmt.setString(1,  utilisateur.getNom());
				pstmt.setString(1,  utilisateur.getPrenom());
				pstmt.setString(1,  utilisateur.getEmail());
				pstmt.setString(1,  utilisateur.getTelephone());
				pstmt.setString(1,  utilisateur.getCode_postal());
				pstmt.setString(1,  utilisateur.getVille());
				pstmt.setString(1,  utilisateur.getMdp());
				pstmt.setInt(1,  utilisateur.getCredit());
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				throw new DALException(languages.getString("addUtilisateurERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException(languages.getString("noData"));
		}
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws DALException, SQLException {
		// TODO Auto-generated method stub
		
	}

}
