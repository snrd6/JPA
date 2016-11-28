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


import be.vdab.dao.VoorstellingenDAO;
import be.vdab.entities.Voorstelling;

@WebServlet(urlPatterns = "/reservatie.htm", name = "reservatieservlet")

public class ReservatieServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatie.jsp";
	private static final String REDIRECT_URL = "/mandje.htm";
	private final transient VoorstellingenDAO voorstellingenDAO=new VoorstellingenDAO();
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Voorstelling voorstelling = null;
		
		HttpSession session = request.getSession();
		
		long voorstellingId = 0;
		
		
		if (request.getParameter("voorstellingId") != null)
		{
			
			voorstellingId = Long.parseLong(request.getParameter("voorstellingId"));
			voorstelling = new VoorstellingenDAO().findVoorstelling(voorstellingId);
	
		
			if (session.getAttribute("mandje") != null )
			{
			
				@SuppressWarnings("unchecked")
				Map<Long, Integer> mandje = ( Map<Long, Integer> ) session.getAttribute("mandje");
				
				
				if ( mandje.containsKey(voorstellingId) )
				{ 
				
					long reedsGereserveerdePlaatsen = mandje.get(voorstellingId);
			
					request.setAttribute("plaatsen", reedsGereserveerdePlaatsen);
				}
			}
		}
		request.setAttribute("voorstelling", voorstelling);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Voorstelling voorstelling = null;
		HttpSession session = request.getSession();
		
		int plaatsen = 0;
		long voorstellingId = 0;
		
		
		Map<String, String> fouten = new HashMap<>();
		Map<Long, Integer> mandje = new HashMap<>();
		

		if (request.getParameter("voorstellingId") != null)
		{
		
			voorstellingId = Integer.parseInt(request.getParameter("voorstellingId"));
			
			voorstelling = voorstellingenDAO.findVoorstelling(voorstellingId);
			
			int vrijePlaatsen = voorstelling.getVrijePlaatsen();
			
			try
			{
	
				plaatsen = Integer.parseInt(request.getParameter("plaatsen"));
				
			
				
				if((plaatsen > vrijePlaatsen) || (plaatsen < 1)) 
				{
					fouten.put("plaatsen", "Tik een getal tussen 1 en " + vrijePlaatsen);
					
				}
			}
			catch(Exception ex) 
			{
				fouten.put("plaatsen", "Vul een getal");
				
			}

		
			if(!fouten.isEmpty()) 
			{ 	
				
				
				request.setAttribute("voorstelling", voorstelling);		
				request.setAttribute("fouten", fouten);					
				
				request.getRequestDispatcher(VIEW).forward(request, response);
			
			}
			else 
			{ 	
				
			
				voorstelling = voorstellingenDAO.findVoorstelling(voorstellingId);
				
			
				if(session.getAttribute("mandje") != null ) 
				{	
				
					
					mandje = (Map<Long, Integer>) session.getAttribute("mandje"); 	
					
					if(mandje.containsKey(voorstellingId))
					{	
						
						
						int vorigePlaatsen = mandje.get(voorstellingId);
						
						if(vorigePlaatsen == plaatsen)
						{ 	
							
							
							request.setAttribute("voorstelling", voorstelling); 	
							request.setAttribute("plaatsen",vorigePlaatsen); 		
							request.getRequestDispatcher(VIEW).forward(request, response);
							
						}
						else
						{ 	
							
							
							mandje.remove(voorstellingId); 						
							mandje.put(voorstellingId, plaatsen); 									
							response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
						}
					}
				
				}
				}
			}
		}
	}
		
	

