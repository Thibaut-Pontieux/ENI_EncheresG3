package fr.eni.encheres.models.bll.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BLLException extends Exception {
	List<String> listeMessagesErreur;
	
	public BLLException() {
		listeMessagesErreur = new ArrayList<String>();
	}
	
	public void ajoutErreur(String message) {
		this.listeMessagesErreur.add(message);
	}
	
	public boolean hasError() {
		return listeMessagesErreur.size() > 0;
	}
	
	public List<String> getListeMessagesErreur() {
		return this.listeMessagesErreur;
	}
}
