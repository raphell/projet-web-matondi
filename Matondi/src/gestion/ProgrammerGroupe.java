package gestion;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.JourDAO;
import type.Groupe;

/**
 * Servlet implementation class ProgrammerGroupe
 */
@WebServlet("/ProgrammerGroupe")
public class ProgrammerGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProgrammerGroupe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet programmer groupe");
		HttpSession session = request.getSession(true);
		
		ArrayList<Groupe> bands = JourDAO.getUnparticipatingBand((int)request.getAttribute("radioAddBand"));
		session.setAttribute("idDayToAddBand",(int)request.getAttribute("radioAddBand"));
		int nbrBands = bands.size();
		System.out.println("nbr of event get by query : "+nbrBands);
	
		session.setAttribute("nbrBands", nbrBands);
		//System.out.println(request.getParameter("nbrEvent"));
		for(int i=0 ; i<nbrBands ; i++) {
			String str ="band"+i;
			session.setAttribute(str, bands.get(i));
		}
		request.getRequestDispatcher("/programmerGroupe.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		System.out.println("doPost programmerBand.");
		if(session.getAttribute("isConnected")==null || (boolean)session.getAttribute("isConnected")==false) {
			response.sendRedirect("identification");
			return;
		}
		ArrayList<Groupe> bands = JourDAO.getUnparticipatingBand(Integer.parseInt(request.getParameter("radioAddBand")));
		session.setAttribute("idDayToAddBand",Integer.parseInt(request.getParameter("radioAddBand")));
		int nbrBands = bands.size();
		System.out.println("nbr of bands get by query : "+nbrBands);
	
		session.setAttribute("nbrBands", nbrBands);
		//System.out.println(request.getParameter("nbrEvent"));
		for(int i=0 ; i<nbrBands ; i++) {
			String str ="band"+i;
			session.setAttribute(str, bands.get(i));
		}
		request.getRequestDispatcher("/programmerGroupe.jsp").forward(request, response);
	}
	
	
	

}
