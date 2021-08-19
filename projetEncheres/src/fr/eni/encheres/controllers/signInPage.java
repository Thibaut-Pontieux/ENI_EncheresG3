package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.models.bll.UtilisateurManager;
import fr.eni.encheres.models.bll.exceptions.BLLException;

/**
 * Servlet implementation class signInPage
 */
@WebServlet("/signInPage")

public class signInPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UtilisateurManager utilisateurMgr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signInPage() {
        super();
        this.utilisateurMgr = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String nom = request.getParameter("nom");
		 String prenom = request.getParameter("prenom");
		 String pseudo = request.getParameter("pseudo");
		 String email = request.getParameter("email");
		 String mdp = request.getParameter("mdp");
		 String phone = request.getParameter("phone");
		 String street = request.getParameter("street");
		 String postalcode = request.getParameter("postalcode");
		 String city = request.getParameter("city");
		  
		 try {
			 utilisateurMgr.ajouterUtilisateur(nom, prenom, pseudo, email, mdp, phone, street, postalcode, city);
		 } catch (BLLException e) {
			 request.setAttribute("erreurs", e.getListeMessagesErreur());
		 }
	       	        
	    this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
	}

}
