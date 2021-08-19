package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import fr.eni.encheres.models.bll.EnchereManager;
import fr.eni.encheres.models.bll.UtilisateurManager;
import fr.eni.encheres.models.bll.exceptions.BLLException;


/**
 * Servlet implementation class connexion
 */
@WebServlet("/connexion")
public class connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UtilisateurManager utilisateurMgr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public connexion() {
        super();
        this.utilisateurMgr = new UtilisateurManager();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("session", "off");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		if (rd != null) {
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			int ID = 0;
			HttpSession session = request.getSession();
			session.setAttribute("session", "on");
			
			request.setAttribute("pseudo", username );
			request.setAttribute("mdp", password);
			
			ID = utilisateurMgr.getUtilisateur(username, password);
			
			if (ID != 0 ) {
				response.sendRedirect(request.getContextPath() + "/encheres");

			}else {
				response.sendRedirect(request.getContextPath() + "/connexion");

			}
			
			
			
		} catch (BLLException e) {
			request.setAttribute("erreurs", e.getListeMessagesErreur());
			
			response.sendRedirect(request.getContextPath() + "/connexion");
			
		}

	
	}

}
