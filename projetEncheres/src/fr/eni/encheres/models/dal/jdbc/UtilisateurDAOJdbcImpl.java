package fr.eni.encheres.models.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import fr.eni.encheres.models.bo.Utilisateur;
import fr.eni.encheres.models.dal.UtilisateurDAO;
import fr.eni.encheres.models.dal.exception.DALException;
import fr.eni.languages.DefaultLanguage;

public class UtilisateurDAOJdbcImpl extends Exception implements UtilisateurDAO {

	ResourceBundle languages = ResourceBundle.getBundle("fr.eni.languages.language", DefaultLanguage.defaultLng);
	
	private static final String SELECT_UN_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	private static final String SELECT_VERIF_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE (pseudo = ? OR email = ?) AND mot_de_passe = ?";
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?,?,?,?,?,?,?,?,?,0,0)";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = 0, administrateur = 0 WHERE no_utilisateur = ? ";
	
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
	public int utilisateurExiste(String pseudo, String mdp) throws DALException, SQLException {
		// TODO Auto-generated method stub
		Connection cnx = null;
		int ID = 0;
		try {
			cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_VERIF_UTILISATEUR);
			pstmt.setString(1, pseudo);
			pstmt.setString(2, pseudo);
			pstmt.setString(3, mdp);
			pstmt.executeQuery();
			ResultSet rs = pstmt.getResultSet();
			while(rs.next()) {
				ID = rs.getInt("no_utilisateur");
			}
			
			
			cnx.close();
		} catch (SQLException e) {
			cnx.close();
			throw new DALException(languages.getString("getUtilisateurERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
		}
		
		return ID;
	}

	@Override
	public void insertUtilisateur(Utilisateur utilisateur) throws DALException, SQLException {
		Connection cnx = null;
		
		if (utilisateur != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_USER);
				pstmt.setString(1,  utilisateur.getPseudo());
				pstmt.setString(2,  utilisateur.getNom());
				pstmt.setString(3,  utilisateur.getPrenom());
				pstmt.setString(4,  utilisateur.getEmail());
				pstmt.setString(5,  utilisateur.getTelephone());
				pstmt.setString(6,  utilisateur.getRue());
				pstmt.setString(7,  utilisateur.getCode_postal());
				pstmt.setString(8,  utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMdp());
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				throw new DALException(languages.getString("ajoutUtilisateurERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException(languages.getString("noData"));
		}
	}

	@Override
	public void updateUtilisateur(Utilisateur utilisateur) throws DALException, SQLException {
		Connection cnx = null;
		
		if (utilisateur != null) {
			try {
				cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER);
				pstmt.setInt(1,  utilisateur.getIdUtilisateur());
				pstmt.setString(2,  utilisateur.getPseudo());
				pstmt.setString(3,  utilisateur.getNom());
				pstmt.setString(4,  utilisateur.getPrenom());
				pstmt.setString(5,  utilisateur.getEmail());
				pstmt.setString(6,  utilisateur.getTelephone());
				pstmt.setString(7,  utilisateur.getRue());
				pstmt.setString(8,  utilisateur.getCode_postal());
				pstmt.setString(9,  utilisateur.getVille());
				pstmt.setString(10, utilisateur.getMdp());
				pstmt.executeUpdate();				
			} catch (SQLException e) {
				throw new DALException(languages.getString("ajoutUtilisateurERR") + " " + languages.getString("srvInfo") + " [" + e.getMessage() + "]");
			}
		} else {
			throw new DALException(languages.getString("noData"));
		} 
		
	}

}
