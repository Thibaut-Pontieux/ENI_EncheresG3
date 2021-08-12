package fr.eni.encheres.models.bo;

public class Utilisateur {
	private int idUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String code_postal;
	private String ville;
	private String mdp;
	private int credit;
	private boolean admin;
	
	public Utilisateur() {
		super();
	}

	
	public Utilisateur(int idUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, String mdp) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mdp = mdp;
	}

	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String code_postal, String ville, String mdp, int credit, boolean admin) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mdp = mdp;
		this.credit = credit;
		this.admin = admin;
	}
	
	
	public Utilisateur(int idUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String code_postal, String ville, String mdp, int credit, boolean admin) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mdp = mdp;
		this.credit = credit;
		this.admin = admin;
	}


	// Accesseurs
	 
	/**
	 * @return the idUtilisateur
	 */
	public int getIdUtilisateur() {
		return idUtilisateur;
	}
	/**
	 * @param idUtilisateur the idUtilisateur to set
	 */
	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * @param rue the rue to set
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * @return the code_postal
	 */
	public String getCode_postal() {
		return code_postal;
	}
	/**
	 * @param code_postal the code_postal to set
	 */
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	/**
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * @param mdp the mdp to set
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	/**
	 * @return the credit
	 */
	public int getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(int credit) {
		this.credit = credit;
	}
	/**
	 * @return the admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
