package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.models.bll.exceptions.BLLException;
import fr.eni.encheres.models.dal.exception.DALException;
import fr.eni.encheres.models.bll.UtilisateurManager;

/**
 * Servlet implementation class profil
 */
@WebServlet("/profil")
public class profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurManager utilisateurMgr;   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public profil() {
		super();
		// TODO Auto-generated constructor stub 
		this.utilisateurMgr = new UtilisateurManager();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/profil.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String pseudo = request.getParameter("pseudo");
		String email = request.getParameter("email");
		String mdp = request.getParameter("mdp");
		String phone = request.getParameter("phone");
		String street = request.getParameter("street");
		String postalcode = request.getParameter("postalcode");
		String city = request.getParameter("city");


		HttpSession session = request.getSession();

		int ID = (int) session.getAttribute("idUser");

		try {
			utilisateurMgr.profilUtilisateur(ID);
		} catch (BLLException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	}

}
