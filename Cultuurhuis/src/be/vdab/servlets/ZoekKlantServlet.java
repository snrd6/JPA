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


@WebServlet(urlPatterns = "/zoekklant.htm", name = "zoekklantservlet")

public class ZoekKlantServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/bevestiging.jsp";
	private static final transient KlantDAO klantdao=new KlantDAO();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	HttpSession session=request.getSession();
	
	if(session.getAttribute("klant")!=null){
		String klantgegevens=klantdao.findKlant( (String) session.getAttribute("klant")).toString();
		
		if (klantgegevens !=null){
			request.setAttribute("klantgegevens", klantgegevens);
		}
	
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		Map<String,String>fouten = new HashMap<>();
		
		
		String paswoord=request.getParameter("paswoord");
		String gebruikersnaam=request.getParameter("gebruikersnaam");
		
		if( (gebruikersnaam!= null) && (paswoord != null))
		{
			if(paswoord.equals(klantdao.getPaswoord(gebruikersnaam))){
				session.setAttribute("klant", gebruikersnaam);
			}
		
			else
			{
				fouten.put("verkeerd", "Verkeerde gebruikersnaam of paswoord");
				session.setAttribute("fouten", fouten);
			}
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
	
}
