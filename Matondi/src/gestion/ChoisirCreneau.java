package gestion;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.CreneauDAO;
import DAO.GroupeDAO;

/**
 * Servlet implementation class ChoisirCreneau
 */
@WebServlet("/ChoisirCreneau")
public class ChoisirCreneau extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoisirCreneau() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet choisirCreneau");
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost choisirCreneau");
		HttpSession session = request.getSession(true);
		System.out.println(request.getParameter("hDeb"));
		if(request.getParameter("hDeb")=="" || request.getParameter("mDeb")=="" || request.getParameter("hFin")=="" || request.getParameter("mFin")=="") {
			session.setAttribute("erreurCreneau", "Il manque une horaire");
			response.sendRedirect("GererEvenement");
		}
		else {
			int hDeb = Integer.parseInt(request.getParameter("hDeb"));
			int mDeb = Integer.parseInt(request.getParameter("mDeb"));
			int hFin = Integer.parseInt(request.getParameter("hFin"));
			int mFin = Integer.parseInt(request.getParameter("mFin"));
			int idDay = Integer.parseInt(request.getParameter("idDaySchedule"));
			int idBand = Integer.parseInt(request.getParameter("idBandSchedule"));
			
			 boolean isCreneauValid = CreneauDAO.isCreneauAvailable(idDay, hDeb, mDeb, hFin, mFin);
			
			if(isCreneauValid) {
				String style = GroupeDAO.getBandById(idBand).getStyle();
				session.setAttribute("erreurCreneau", null);
				CreneauDAO.addCreneau(idDay, hDeb, mDeb, hFin, mFin, idBand, style);
				request.getRequestDispatcher("GererEvenement").forward(request, response);
			}
			else {
				session.setAttribute("erreurCreneau", "Une partie de ce créneau est déjà occupée");
				response.sendRedirect("GererEvenement");
			}
		}
	}
}
