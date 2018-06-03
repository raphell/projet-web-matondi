package gestion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.OrganisateurDAO;

/**
 * Servlet implementation class identifier
 */
@WebServlet("/identification")
public class Identifier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Identifier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGetIdentifier");
		HttpSession session = request.getSession(true);
		
		String identifiant = request.getParameter("identifiant");
		String motDePasse = request.getParameter("motDePasse");
		System.out.println(session.getAttribute("isConnected"));
		if(session.getAttribute("isConnected")!=null && (boolean)session.getAttribute("isConnected")==true) {
			System.out.println("isconnected trueeeeeeeee");
			//response.sendRedirect("gestion");
			request.getRequestDispatcher("/identification.jsp").forward(request, response);
		}
		else {
			if(identifiant==null) {
				System.out.println("id null");
				identifiant="";
			}
			if(motDePasse==null) {
				motDePasse="";
			}
			session.setAttribute("identifiant", identifiant);
			session.setAttribute("motDePasse", motDePasse);
			
			System.out.println("id "+identifiant);
			System.out.println("mdp"+motDePasse);
			
			request.getRequestDispatcher("/identification.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String identifiant = request.getParameter("identifiant");
		String motDePasse = request.getParameter("motDePasse");
		System.out.println("doPostIdentifier");
		System.out.println(identifiant);
		System.out.println(motDePasse);
		
		HttpSession session = request.getSession(true);
		
		session.setAttribute("identifiant", identifiant);
		session.setAttribute("motDePasse", motDePasse);
		
		
		int idOrga = OrganisateurDAO.isOrgaValid(identifiant, motDePasse);
		session.setAttribute("idOrga", idOrga);
		System.out.println(session.getAttribute("idOrga"));
		if(idOrga!=-1) {
			System.out.println("mot de passe et id valides");
			session.setAttribute("isConnected", true);
			session.setAttribute("errorMsg", "");
			request.getRequestDispatcher("gestion").forward(request, response);	
		}
		else {
			session.setAttribute("isConnected",false);
			session.setAttribute("errorMsg", "Mauvais identifiant / mot de passe");
			//response.sendRedirect("identification");
			request.getRequestDispatcher("/identification.jsp").forward(request, response);
		}
	}

}
