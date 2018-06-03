package accueil;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.DAOContext;
import DAO.EvenementDAO;
import type.Evenement;
import type.Jour;


@WebServlet(urlPatterns="/accueil", loadOnStartup=1)
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	@Override
	public void init() throws ServletException {
		super.init();
		DAOContext.init(this.getServletContext() );
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet ACCUEIL");
		HttpSession session = request.getSession(true);
		
		
		ArrayList<Evenement> events = EvenementDAO.getIncomingEvents();
		int nbrEvent = events.size();
		System.out.println("nbr of day get by query : "+nbrEvent);
		session.setAttribute("nbrEventToDisplay", nbrEvent);
		for(int i=0 ; i<nbrEvent ; i++) {
			String str ="eventToDisplay"+i;
			session.setAttribute(str, events.get(i));
		}
		request.getRequestDispatcher("/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
