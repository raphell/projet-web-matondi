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
 * Servlet implementation class creerGroupe
 */
@WebServlet("/creationGroupe")
public class CreationGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreationGroupe() {
        super();
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		System.out.println(session.getAttribute("isConnected"));
		if(session.getAttribute("isConnected")==null || (boolean)session.getAttribute("isConnected")==false) {
			response.sendRedirect("identification");
			return;
		}
		
		request.getRequestDispatcher("/creationGroupe.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost creerEvent");
		
		String nameBand = request.getParameter("nameBand");
		String styleBand = request.getParameter("styleBand");
		String mailBand = request.getParameter("mailBand");
		String phoneBand = request.getParameter("phoneBand");
		int nbrPeopleBand = Integer.parseInt(request.getParameter("nbrPeopleBand"));
		System.out.println("name band to add :"+nameBand);

		int nbrLine = GroupeDAO.addBand(nameBand, nbrPeopleBand, styleBand, mailBand, phoneBand);
		HttpSession session = request.getSession(true);

		if(nbrLine==1) {/*
			Groupe newGroupe = GroupeDAO.getBandByName(nameBand);

			session.setAttribute("nameBand", newGroupe.getName());
			session.setAttribute("nbrPeopleBand", newGroupe.getNbrPeople());
			session.setAttribute("styleBand", newGroupe.getStyle());
			session.setAttribute("mailBand", newGroupe.getMail());
			session.setAttribute("phoneBand", newGroupe.getPhone());
			*/
			request.getRequestDispatcher("gestion").forward(request, response);
		}
		else {
			session.setAttribute("error", "Ce nom est déjà prit");
			response.sendRedirect("creationEvenement");
		}
	}
	

}
