package gestion;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.EvenementDAO;
import DAO.GroupeDAO;
import type.Evenement;
import type.Groupe;

/**
 * Servlet implementation class ModifyEvent
 */
@WebServlet("/ModifyEvent")
public class ModifyEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyEvent() {
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
		int idEvent = (int)session.getAttribute("idEventManaged");
		
		Evenement event = EvenementDAO.getEventById(idEvent);
		session.setAttribute("modifEvent", event);
		
		request.getRequestDispatcher("/modifierEvent.jsp").forward(request, response);
	
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
		
		String nameEvent = request.getParameter("nameBand");
		Date dateEvent = Date.valueOf(request.getParameter("dateEvent"));
		String cityEvent = request.getParameter("cityEvent");
		String streetEvent = request.getParameter("streetEvent");
		int streetNbrEvent = Integer.parseInt(request.getParameter("streetNumberEvent"));
		int nbrDayEvent = Integer.parseInt(request.getParameter("nbrDayEvent"));
		int idEvent = (int)session.getAttribute("idEventManaged");
		
		int nbrLine = EvenementDAO.modifyEvent(nameEvent, dateEvent, cityEvent, streetEvent, streetNbrEvent, nbrDayEvent, idEvent);

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
