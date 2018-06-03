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
import DAO.JourDAO;
import DAO.GroupeDAO;
import type.Creneau;
import type.Evenement;
import type.Groupe;
import type.Jour;

/**
 * Servlet implementation class GererEvenement
 */
@WebServlet("/GererEvenement")
public class GererEvenement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GererEvenement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet gerer event");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("isConnected")==null || (boolean)session.getAttribute("isConnected")==false) {
			response.sendRedirect("identification");
			return;
		}
		int idEvent;
		
		if(request.getParameter("radio")==null) {;
			idEvent = (int)session.getAttribute("idEventManaged");
		}
		else {
			System.out.println(session.getAttribute("idEventManaged"));
			idEvent = Integer.parseInt(request.getParameter("radio"));
		}
		
		System.out.println("id Event :"+idEvent);
		
		Evenement event = EvenementDAO.getEventById(idEvent);
		session.setAttribute("idEventManaged", idEvent);
		System.out.println("name event :"+event.getName());
		session.setAttribute("nameEvent", event.getName());
		session.setAttribute("dateEvent", event.getDate());
		session.setAttribute("cityEvent", event.getCity());
		session.setAttribute("streetEvent", event.getStreet());
		session.setAttribute("streetNbrEvent", event.getNbrStreet());
		session.setAttribute("nbrDayEvent", event.getNbrDay());
		System.out.println("NOM EVENEMENT "+session.getAttribute("nameEvent"));
		
		ArrayList<Jour> eventDays = EvenementDAO.getEventDays(idEvent);
		int nbrDay = eventDays.size();
		System.out.println("nbr of day get by query : "+nbrDay);
		session.setAttribute("nbrDayEvent", nbrDay);
		System.out.println(nbrDay);
		for(int i=0 ; i<nbrDay ; i++) {
			System.out.println("ID Jour :"+eventDays.get(i).getIdJour());
			String str ="day"+i;
			session.setAttribute(str, eventDays.get(i));
		}
		
		int nbrCreneaux;
		int nbrProgrammedBand;
		for(int i=0 ; i<nbrDay ;i++) {
			ArrayList<Creneau> creneauxJour = JourDAO.getCreneaux(eventDays.get(i).getIdJour());
			nbrCreneaux = creneauxJour.size();
			session.setAttribute("nbrCreneau"+i, nbrCreneaux);
			for(int j=0 ; j<nbrCreneaux ; j++) {
				String str ="creneau"+i+j;
				session.setAttribute(str, creneauxJour.get(j));
			}
			
			ArrayList<Groupe> programmedBands = JourDAO.getParticipatingBand(eventDays.get(i).getIdJour());
			nbrProgrammedBand = programmedBands.size();
			session.setAttribute("nbrBands"+i, nbrProgrammedBand);
			for(int j=0 ; j<nbrProgrammedBand ; j++) {
				String str ="band"+i+j;
				session.setAttribute(str, programmedBands.get(j));
			}
		}
		request.getRequestDispatcher("/gestionEvenement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		System.out.println("doPost gererEvent");
		if(session.getAttribute("isConnected")==null || (boolean)session.getAttribute("isConnected")==false) {
			response.sendRedirect("identification");
			return;
		}
		
		if(request.getParameter("cost")!=null) {
			int cost = Integer.parseInt(request.getParameter("cost"));
			int idBand = Integer.parseInt(request.getParameter("radio"));
			int idJour = (int) session.getAttribute("idDayToAddBand");
			GroupeDAO.addGroupeToDay(idJour, idBand, cost);
		}
		
		int idEvent;
		if(session.getAttribute("idEventManaged")==null) {
			idEvent = Integer.parseInt(request.getParameter("radio"));
			session.setAttribute("idEventManaged", idEvent);
		}
		else {
			idEvent = (int)session.getAttribute("idEventManaged");
		}
		
		ArrayList<Jour> eventDays = EvenementDAO.getEventDays(idEvent);
		int nbrDay = eventDays.size();
		System.out.println("nbr of day get by query : "+nbrDay);
		session.setAttribute("nbrDayEvent", nbrDay);
		System.out.println(nbrDay);
		for(int i=0 ; i<nbrDay ; i++) {
			System.out.println("ID Jour :"+eventDays.get(i).getIdJour());
			String str ="day"+i;
			session.setAttribute(str, eventDays.get(i));
		}
		System.out.println("day 1 (dogetGererEvent) :"+session.getAttribute("day"+0).toString());
		int nbrCreneaux;
		int nbrProgrammedBand;
		for(int i=0 ; i<nbrDay ;i++) {
			ArrayList<Creneau> creneauxJour = JourDAO.getCreneaux(eventDays.get(i).getIdJour());
			nbrCreneaux = creneauxJour.size();
			session.setAttribute("nbrCreneau"+i, nbrCreneaux);

			for(int j=0 ; j<nbrCreneaux ; j++) {
				String str ="creneau"+i+j;
				session.setAttribute(str, creneauxJour.get(j));
			}
			
			ArrayList<Groupe> programmedBands = JourDAO.getParticipatingBand(eventDays.get(i).getIdJour());
			nbrProgrammedBand = programmedBands.size();
			session.setAttribute("nbrBands"+i, nbrProgrammedBand);

			for(int j=0 ; j<nbrProgrammedBand ; j++) {
				String str ="band"+i+j;
				session.setAttribute(str, programmedBands.get(j));
			}
		}		
		request.getRequestDispatcher("/gestionEvenement.jsp").forward(request, response);
	}

}
