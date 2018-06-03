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
import DAO.OrganisateurDAO;
import type.Evenement;

/**
 * Servlet implementation class gestion
 */
@WebServlet("/gestion")
public class gestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet Gestion");
		
		HttpSession session = request.getSession(true);
		session.setAttribute("errorCreateEvent", null);
		session.setAttribute("erreurCreneau", null);
		
		if(session.getAttribute("isConnected")==null) {
			String identifiant = request.getParameter("identifiant");
			String motDePasse = request.getParameter("motDePasse");
			System.out.println(identifiant);
			System.out.println(motDePasse);
			
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

				request.getRequestDispatcher("identification").forward(request, response);
			}
		}
		else {
			ArrayList<Evenement> incomingEvents = EvenementDAO.getIncomingEvents();
			int nbrIncomingEvents = incomingEvents.size();
			System.out.println("nbr of event get by query : "+nbrIncomingEvents);
		
			session.setAttribute("nbrEvent", nbrIncomingEvents);

			for(int i=0 ; i<nbrIncomingEvents ; i++) {
				String str ="incomingEvent"+i;
				session.setAttribute(str, incomingEvents.get(i));
			}
			request.getRequestDispatcher("/gestion.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost gestion");
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("isConnected")==null) {
				request.getRequestDispatcher("identification").forward(request, response);
		}
		else {
			ArrayList<Evenement> incomingEvents = EvenementDAO.getIncomingEvents();
			int nbrIncomingEvents = incomingEvents.size();
			System.out.println("nbr of event get by query : "+nbrIncomingEvents);
		
			session.setAttribute("nbrEvent", nbrIncomingEvents);
			//System.out.println(request.getParameter("nbrEvent"));
			for(int i=0 ; i<nbrIncomingEvents ; i++) {
				String str ="incomingEvent"+i;
				session.setAttribute(str, incomingEvents.get(i));
			}
			request.getRequestDispatcher("/gestion.jsp").forward(request, response);
		}
		
	}
}


/*
JsonObjectBuilder jsonEvent = Json.createObjectBuilder();
JsonArrayBuilder jsonEventArray = Json.createArrayBuilder();
for (int i=0 ; i<nbrIncomingEvents ; i++) {
	JsonObject newEvent = createJsonEvent(incomingEvents.get(i));
	jsonEventArray.add(newEvent);	
	System.out.println("array element :"+newEvent);
}
JsonArray jArrayEvent = jsonEventArray.build();
System.out.println("array elem  :"+jArrayEvent);

jsonEvent.add("OfEvent", nbrIncomingEvents);
jsonEvent.add("Events", jArrayEvent);
JsonObject buildEvents = jsonEvent.build();

System.out.println(buildEvents);
session.setAttribute("incomingEvents",buildEvents);

System.out.println("sessionAttribut :"+session.getAttribute("incomingEvents"));
*/
