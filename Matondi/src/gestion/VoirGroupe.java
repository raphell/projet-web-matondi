package gestion;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.GroupeDAO;
import DAO.JourDAO;
import type.Groupe;

/**
 * Servlet implementation class VoirGroupe
 */
@WebServlet("/VoirGroupe")
public class VoirGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoirGroupe() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		System.out.println("doPost programmerBand.");
		
		if(session.getAttribute("isConnected")==null || (boolean)session.getAttribute("isConnected")==false) {
			response.sendRedirect("identification");
			return;
		}
		
		ArrayList<Groupe> allBands = GroupeDAO.getAllBands();
		int nbrAllBands = allBands.size();
	
		session.setAttribute("nbrAllBands", nbrAllBands);
		for(int i=0 ; i<nbrAllBands ; i++) {
			String str ="allBand"+i;
			session.setAttribute(str, allBands.get(i));
		}
		request.getRequestDispatcher("/voirGroupes.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
