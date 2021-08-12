package fr.eni.encheres.models.bo;

public class Categorie {
	private int idCategorie;
	private String libelle;
	
	public Categorie() {
		super();
	}
	public Categorie(String libelle) {
		super();
		this.libelle = libelle;
	}
	public Categorie(int idCategorie, String libelle) {
		super();
		this.idCategorie = idCategorie;
		this.libelle = libelle;
	}
	/**
	 * @return the idCategorie
	 */
	public int getIdCategorie() {
		return idCategorie;
	}
	/**
	 * @param idCategorie the idCategorie to set
	 */
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
}
