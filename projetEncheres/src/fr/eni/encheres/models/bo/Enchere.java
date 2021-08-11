package fr.eni.encheres.models.bo;

import java.util.Date;

public class Enchere {
	private int idUtilisateur;
	private int idArticle;
	private Date date_enchere;
	private int montant;
	
	public Enchere() {
		super();
	}
	
	public Enchere(int idUtilisateur, int idArticle, Date date_enchere, int montant) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.idArticle = idArticle;
		this.date_enchere = date_enchere;
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
	public Date getDate_enchere() {
		return date_enchere;
	}

	/**
	 * @param date_enchere the date_enchere to set
	 */
	public void setDate_enchere(Date date_enchere) {
		this.date_enchere = date_enchere;
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
	
	
	
}
