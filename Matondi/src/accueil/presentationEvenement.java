package accueil;

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
import type.Creneau;
import type.Evenement;
import type.Jour;

/**
 * Servlet implementation class presentationEvenement
 */
@WebServlet("/presentationEvenement")
public class presentationEvenement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public presentationEvenement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		System.out.println("doGet presentation");
		
		int idEvent = Integer.parseInt(request.getParameter("radio"));
		Evenement eventDisplay = EvenementDAO.getEventById(idEvent);
		session.setAttribute("NameDisplay", eventDisplay.getName());
		session.setAttribute("NbrDayDisplay", eventDisplay.getNbrDay());
		session.setAttribute("cityDisplay", eventDisplay.getCity());
		session.setAttribute("StreetDisplay", eventDisplay.getStreet());
		session.setAttribute("StreetNbrDisplay", eventDisplay.getNbrStreet());
		session.setAttribute("DateDisplay", eventDisplay.getDate());
		
		
		Evenement event = EvenementDAO.getEventById(idEvent);
		
		session.setAttribute("nameEvent", event.getName());
		ArrayList<Jour> eventDays = EvenementDAO.getEventDays(idEvent);
		int nbrDay = eventDays.size();
		System.out.println("nbr of day get by query : "+nbrDay);
		session.setAttribute("nbrDayEventDisplay", nbrDay);
		System.out.println(nbrDay);
		for(int i=0 ; i<nbrDay ; i++) {
			System.out.println("ID Jour :"+eventDays.get(i).getIdJour());
			String str ="dayDisplay"+i;
			session.setAttribute(str, eventDays.get(i));
		}
		
		int nbrCreneaux;
		for(int i=0 ; i<nbrDay ;i++) {
			ArrayList<Creneau> creneauxJour = JourDAO.getCreneaux(eventDays.get(i).getIdJour());
			nbrCreneaux = creneauxJour.size();
			session.setAttribute("nbrCreneauDisplay"+i, nbrCreneaux);
			for(int j=0 ; j<nbrCreneaux ; j++) {
				String str ="creneauDisplay"+i+j;
				session.setAttribute(str, creneauxJour.get(j));
			}
		}
		
		
		request.getRequestDispatcher("/presentationEvenement.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
