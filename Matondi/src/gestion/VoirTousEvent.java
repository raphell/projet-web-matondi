package gestion;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.EvenementDAO;
import DAO.GroupeDAO;
import DAO.JourDAO;
import type.Evenement;
import type.Groupe;

/**
 * Servlet implementation class VoirTousEvent
 */
@WebServlet("/VoirTousEvenements")
public class VoirTousEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoirTousEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet voir all events");
		HttpSession session = request.getSession(true);
		
		ArrayList<Evenement> allEvents = EvenementDAO.getAllEvents();
		int nbrAllEvents = allEvents.size();
	
		session.setAttribute("nbrAllEvents", nbrAllEvents);
		for(int i=0 ; i<nbrAllEvents ; i++) {
			String str ="allEvent"+i;
			session.setAttribute(str, allEvents.get(i));
		}
		request.getRequestDispatcher("/tousLesEvenement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
