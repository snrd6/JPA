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
import be.vdab.dao.MandjeDAO;
import be.vdab.dao.ReservatiesDAO;
import be.vdab.entities.Klant;
import be.vdab.entities.MandjeItem;

@WebServlet(urlPatterns = "/boeking.htm", name = "boekingservlet")

public class BoekingServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/boeking.jsp";
	private final transient MandjeDAO mandjeDAO=new MandjeDAO();
	private final transient KlantDAO klantDAO=new KlantDAO();
	private final transient ReservatiesDAO reservatiesDAO=new ReservatiesDAO();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Map<Long, Integer> mislukt = new HashMap<>();
		Map<Long, Integer> gelukt = new HashMap<>();
		
		if ((session.getAttribute("mandje") != null) && (session.getAttribute("klant") != null))
		{
			@SuppressWarnings("unchecked")
			Map<Long, Integer> mandje = (Map<Long, Integer>) session.getAttribute("mandje"); 	
			Klant klant = klantDAO.findKlant((String) session.getAttribute("klant")); 									
			long klantnr = klant.getKlantId();
			
			for(Map.Entry<Long, Integer> entry : mandje.entrySet())
			{
				long voorstellingsnr = entry.getKey();
				int plaatsen = entry.getValue();
				
				if( reservatiesDAO.boekReservaties(klantnr, voorstellingsnr, plaatsen))
				{
					gelukt.put(entry.getKey(), entry.getValue());
				}
				else
				{
					mislukt.put(entry.getKey(), entry.getValue());
				}
			}
			
			Iterable<MandjeItem> mandjeItemsGelukt = mandjeDAO.geefMandje(gelukt);
			Iterable<MandjeItem> mandjeItemsMislukt = mandjeDAO.geefMandje(mislukt);
			request.setAttribute("mandjeItemsGelukt",mandjeItemsGelukt);
			request.setAttribute("mandjeItemsMislukt",mandjeItemsMislukt);
			session.removeAttribute("mandje");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
		request.getSession().invalidate();
	}
}
