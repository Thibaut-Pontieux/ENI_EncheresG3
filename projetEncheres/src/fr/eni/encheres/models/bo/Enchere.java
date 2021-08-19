package fr.eni.encheres.models.bo;

import java.time.LocalDate;
import java.util.Date;

public class Enchere {
	private int idUtilisateur;
	private int idArticle;
	private int idCategorie;
	private Date dateEnchere;
	private int montant;
	private String nomArticle;
	private String description;
	private Date dateDebutEnchere;
	private Date dateFinEnchere;
	private int prixInitial;
	private int prixFinal;
	private Utilisateur utilisateur;
	private LocalDate dDebEnchere;
	private LocalDate dFinEnchere;

	public Enchere() {
		super();
	}
	
	
	
	public Enchere(int idUtilisateur, int idCategorie, String nomArticle, String description, int prixInitial, LocalDate dateDebutEnchere,
			LocalDate dateFinEnchere) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.idCategorie = idCategorie;
		this.nomArticle = nomArticle;
		this.description = description;
		this.prixInitial = prixInitial;
		this.dDebEnchere = dateDebutEnchere;
		this.dFinEnchere = dateFinEnchere;
	}



	public Enchere(int idUtilisateur, int idArticle, Date dateEnchere, int montant) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.idArticle = idArticle;
		this.dateEnchere = dateEnchere;
		this.montant = montant;
	}

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
	 * @return the idArticle
	 */
	public int getIdArticle() {
		return idArticle;
	}

	/**
	 * @param idArticle the idArticle to set
	 */
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}

	/**
	 * @return the date_enchere
	 */
	public Date getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * @param date_enchere the date_enchere to set
	 */
	public void setDate_enchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	/**
	 * @return the montant
	 */
	public int getMontant() {
		return montant;
	}

	/**
	 * @param montant the montant to set
	 */
	public void setMontant(int montant) {
		this.montant = montant;
	}

	public int getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateDebutEnchere() {
		return dateDebutEnchere;
	}

	public void setDateDebutEnchere(Date dateDebutEnchere) {
		this.dateDebutEnchere = dateDebutEnchere;
	}

	public Date getDateFinEnchere() {
		return dateFinEnchere;
	}

	public void setDateFinEnchere(Date dateFinEnchere) {
		this.dateFinEnchere = dateFinEnchere;
	}

	public int getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}

	public int getPrixFinal() {
		return prixFinal;
	}

	public void setPrixFinal(int prixFinal) {
		this.prixFinal = prixFinal;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}



	public LocalDate getdDebEnchere() {
		return dDebEnchere;
	}



	public void setdDebEnchere(LocalDate dDebEnchere) {
		this.dDebEnchere = dDebEnchere;
	}



	public LocalDate getdFinEnchere() {
		return dFinEnchere;
	}



	public void setdFinEnchere(LocalDate dFinEnchere) {
		this.dFinEnchere = dFinEnchere;
	}

	
}
