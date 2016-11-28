package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Klant;

@WebServlet(urlPatterns = "/zoekklant.htm", name = "zoekklantservlet")

public class ZoekKlantServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/bevestiging.jsp";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		Map<String,String>fouten = new HashMap<>();
		
		Klant klant = null;
		
		if( (request.getParameter("gebruikersnaam")!= null) && (request.getParameter("paswoord") != null))
		{
						
			klant = new KlantDAO().findKlant(request.getParameter("gebruikersnaam"),
			request.getParameter("paswoord"));
			
			if(klant != null)
			{
				session.setAttribute("klant", klant);
			}
			else
			{
				fouten.put("verkeerd", "Verkeerde gebruikersnaam of paswoord");
				request.setAttribute("fouten", fouten);
			}
		}
				
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
