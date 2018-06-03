package gestion;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.EvenementDAO;
import DAO.JourDAO;
import type.Creneau;
import type.Evenement;
import type.Groupe;
import type.Jour;

/**
 * Servlet implementation class creerEvenement
 */
@WebServlet("/creationEvenement")
public class CreationEvenement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreationEvenement() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		if (session.getAttribute("isConnected") == null) {
			// request.getRequestDispatcher("/identification.jsp").forward(request,
			// response);
			response.sendRedirect("identification");
			return;
		}

		System.out.println("CONNECTE OUI");
		request.getRequestDispatcher("/creationEvenement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost creation Event");

		String nameEvent = request.getParameter("nameEvent");
		String cityEvent = request.getParameter("cityEvent");
		String streetEvent = request.getParameter("streetEvenement");
		String numStreetEvent = request.getParameter("streetNumberEvent");
		int streetNumberEvent;
		if (numStreetEvent != "") {
			streetNumberEvent = Integer.parseInt(numStreetEvent);
		} else {
			streetNumberEvent = -1;
		}
		int nbrDayEvent = Integer.parseInt(request.getParameter("nbrDayEvent"));

		Date dateEvent = Date.valueOf(request.getParameter("dateEvent"));
		System.out.println("date :" + dateEvent);
		int nbrLine = EvenementDAO.addEvenement(nameEvent, cityEvent, streetEvent, streetNumberEvent, dateEvent,
				nbrDayEvent);

		HttpSession session = request.getSession(true);

		if (nbrLine == 1) {
			session.setAttribute("errorCreateEvent", null);
			Evenement newEvent = EvenementDAO.getEventByName(nameEvent);
			// System.out.println("event recuperé"+);
			session.setAttribute("nameEvent", newEvent.getName());
			session.setAttribute("dateEvent", newEvent.getDate());
			session.setAttribute("cityEvent", newEvent.getCity());
			session.setAttribute("streetEvent", newEvent.getStreet());
			session.setAttribute("streetNbrEvent", newEvent.getNbrStreet());
			session.setAttribute("nbrDayEvent", newEvent.getNbrDay());
			session.setAttribute("error", "");

			ArrayList<Jour> eventDays = EvenementDAO.getEventDays(newEvent.getId());
			int nbrDay = eventDays.size();
			System.out.println("nbr of day get by query : " + nbrDay);
			session.setAttribute("nbrDayEvent", nbrDay);
			System.out.println(nbrDay);
			for (int i = 0; i < nbrDay; i++) {
				System.out.println("ID Jour :" + eventDays.get(i).getIdJour());
				String str = "day" + i;
				session.setAttribute(str, eventDays.get(i));
			}

			int nbrCreneaux;
			int nbrProgrammedBand;
			for (int i = 0; i < nbrDay; i++) {
				ArrayList<Creneau> creneauxJour = JourDAO.getCreneaux(eventDays.get(i).getIdJour());
				nbrCreneaux = creneauxJour.size();
				session.setAttribute("nbrCreneau" + i, nbrCreneaux);

				for (int j = 0; j < nbrCreneaux; j++) {
					String str = "creneau" + i + j;
					session.setAttribute(str, creneauxJour.get(i));
				}

				ArrayList<Groupe> programmedBands = JourDAO.getParticipatingBand(eventDays.get(i).getIdJour());
				nbrProgrammedBand = programmedBands.size();
				session.setAttribute("nbrBands" + i, nbrProgrammedBand);

				for (int j = 0; j < nbrProgrammedBand; j++) {
					String str = "band" + i + j;
					session.setAttribute(str, programmedBands.get(i));
				}
			}
			request.getRequestDispatcher("/gestionEvenement.jsp").forward(request, response);
		} else {
			session.setAttribute("errorCreateEvent", "Ce nom est déjà prit");
			response.sendRedirect("creationEvenement");
		}
	}

}
