package fr.eni.encheres;

import java.io.IOException;
import java.util.regex.Pattern;

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
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/signInPage.jsp");
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
		 String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
		 String phoneregex = "/^((\\+|00)33\\s?|0)[1-59](\\s?\\d{2}){4}$/";
		 String postalcoderegex = "/^0[1-9]|[1-8][0-9]|9[0-8]|2A|2B[0-9]{3}$/";
		 
		 Pattern pattern = Pattern.compile(regex);
		 Pattern phonepattern = Pattern.compile(phoneregex);
		 Pattern postalcodepattern = Pattern.compile(postalcoderegex);
		 
		 if (pattern.matcher(email).matches() == false) {
			 request.setAttribute("error", "format invalid");
		 }
		 
		 if (phonepattern.matcher(phone).matches() == false) {
			 request.setAttribute("errorphone", "format invalid");
		 }
		 
		 if (postalcodepattern.matcher(postalcode).matches() == false) {
			 request.setAttribute("errorpostalcode", "format invalid");
		 }
	
	        
        request.setAttribute("nom", nom);
        request.setAttribute("prenom", prenom);
        request.setAttribute("pseudo", pseudo);
        request.setAttribute("email", email);
        request.setAttribute("mdp", mdp);
        request.setAttribute("phone", phone);
        request.setAttribute("street", street);
        request.setAttribute("postalcode", postalcode);
        request.setAttribute("city", city);
	        
	        this.getServletContext().getRequestDispatcher("/WEB-INF/signInPage.jsp").forward(request, response);
	}

}
