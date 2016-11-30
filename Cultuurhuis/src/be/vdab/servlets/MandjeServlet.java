package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.dao.MandjeDAO;
import be.vdab.dao.VoorstellingenDAO;
import be.vdab.entities.MandjeItem;

@WebServlet(urlPatterns = "/mandje.htm", name = "mandjeservlet")

public class MandjeServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/mandje.jsp";
	private static final String REDIRECT_URL = "/mandje.htm";
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if(session.getAttribute("mandje") != null)
		{
			
			BigDecimal totaalPrijs = BigDecimal.ZERO;
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandjeMap = (Map<Long, Integer>) session.getAttribute("mandje"); 	
			
		
			for(Map.Entry<Long, Integer> entry : mandjeMap.entrySet())
			{
				BigDecimal prijs = new VoorstellingenDAO().findVoorstelling(entry.getKey()).getPrijs();
				BigDecimal aantalPlaatsen = new BigDecimal(entry.getValue());
				BigDecimal totaal = prijs.multiply(aantalPlaatsen);
				totaalPrijs = totaalPrijs.add(totaal);
			}
			
			Iterable<MandjeItem> mandjeItems = new MandjeDAO().geefMandje(mandjeMap);
			request.setAttribute("mandjeItems", mandjeItems);		
			request.setAttribute("totaalprijs", totaalPrijs);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if(request.getParameterValues("id") != null)
		{
			
			
			Map<Integer, Integer> mandje = (Map<Integer, Integer>)session.getAttribute("mandje");
			
			for(String id : request.getParameterValues("id"))
			{
				mandje.remove(Integer.parseInt(id));
			}
			session.setAttribute("mandje", mandje);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
		}
		else
		{   
		
			
			BigDecimal totaalPrijs = BigDecimal.ZERO;
			Map<Long, Integer> mandjeMap = (Map<Long, Integer>) session.getAttribute("mandje"); 
	
			for(Map.Entry<Long, Integer> entry : mandjeMap.entrySet())
			{
				BigDecimal prijs = new VoorstellingenDAO().findVoorstelling(entry.getKey()).getPrijs();
				BigDecimal aantalPlaatsen = new BigDecimal(entry.getValue());
				BigDecimal totaal = prijs.multiply(aantalPlaatsen);
				totaalPrijs = totaalPrijs.add(totaal);
			}
			
			Iterable<MandjeItem> mandjeItems = new MandjeDAO().geefMandje(mandjeMap);
			request.setAttribute("mandjeItems", mandjeItems);		
			request.setAttribute("totaalprijs", totaalPrijs);
			
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
