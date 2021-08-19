package fr.eni.encheres.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.models.bll.EnchereManager;
import fr.eni.encheres.models.bll.UtilisateurManager;
import fr.eni.encheres.models.bll.exceptions.BLLException;
import fr.eni.encheres.models.bo.Categorie;
import fr.eni.encheres.models.bo.Utilisateur;

/**
 * Servlet implementation class ajoutEnchreres
 */
@WebServlet("/ajoutEncheres")
public class ajoutEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private EnchereManager enchereMgr;
	private UtilisateurManager utilisateurMgr;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajoutEncheres() {
        super();
        this.enchereMgr = new EnchereManager();
        this.utilisateurMgr = new UtilisateurManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<Categorie> listeC = new ArrayList<Categorie>();
		HttpSession session = request.getSession();
		
		try {
			listeC = enchereMgr.getCategories();
			request.setAttribute("categoriesEncheres", listeC);
			if (session.getAttribute("idUser") != null) {
				Utilisateur u = utilisateurMgr.getUtilisateur((Integer) session.getAttribute("idUser"));
				request.setAttribute("infosUtilisateur", u);
			}
		} catch (BLLException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ajoutEncheres.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String categorie = request.getParameter("catEnchere");
		String nomArticle = request.getParameter("nomArticle");
		String descArticle = request.getParameter("descriptionArticle");
		int prixArticle = Integer.parseInt(request.getParameter("prixArticle"));
		String dateDebVente = request.getParameter("dateDebVente");
		String heureDebVente = request.getParameter("heureDebVente");
		String dateFinVente = request.getParameter("dateFinVente");
		String heureFinVente = request.getParameter("heureFinVente");
		HttpSession session = request.getSession();
		
		try {
			enchereMgr.ajouterArticle((Integer) session.getAttribute("idUser"), Integer.parseInt(categorie),nomArticle, descArticle, prixArticle, dateDebVente, dateFinVente, heureDebVente, heureFinVente);
			response.sendRedirect(request.getContextPath() + "/mesEncheres");
		} catch (BLLException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
			doGet(request, response);
		}
	}

}
