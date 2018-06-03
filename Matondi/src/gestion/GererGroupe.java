package gestion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.GroupeDAO;
import type.Groupe;

/**
 * Servlet implementation class GererGroupe
 */
@WebServlet("/GererGroupe")
public class GererGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GererGroupe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("isConnected")==null || (boolean)session.getAttribute("isConnected")==false) {
			response.sendRedirect("identification");
			return;
		}
		int idGroupe = Integer.parseInt(request.getParameter("radio"));
		Groupe band = GroupeDAO.getBandById(idGroupe);
		session.setAttribute("displayBand", band);
		
		request.getRequestDispatcher("/gererGroupe.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost gererGroupe");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("isConnected")==null || (boolean)session.getAttribute("isConnected")==false) {
			response.sendRedirect("identification");
			return;
		}
		String nameBand = request.getParameter("nameBand");
		String styleBand = request.getParameter("styleBand");
		String mailBand = request.getParameter("mailBand");
		String phoneBand = request.getParameter("phoneBand");
		int nbrPeopleBand = Integer.parseInt(request.getParameter("nbrPeopleBand"));
		int idBand = ((Groupe)session.getAttribute("displayBand")).getId();
		
		int nbrLine = GroupeDAO.modifyBand(nameBand, nbrPeopleBand, styleBand, mailBand, phoneBand, idBand);

		if(nbrLine==1) {
			session.setAttribute("errorModifBand", null);
			request.getRequestDispatcher("VoirGroupe").forward(request, response);
		}
		else {
			session.setAttribute("errorModifBand", "Ce nom est déjà prit");
			request.getRequestDispatcher("/gererGroupe.jsp").forward(request, response);
		}

		
	}

}
