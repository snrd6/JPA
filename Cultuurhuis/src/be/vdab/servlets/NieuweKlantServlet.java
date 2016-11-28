package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.dao.KlantDAO;
import be.vdab.entities.Klant;

@WebServlet(urlPatterns = "/nieuweklant.htm", name = "nieuweklantservlet")

public class NieuweKlantServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/nieuweklant.jsp";
	private static final String REDIRECT_URL = "/bevestiging.htm";

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if(session.getAttribute("klant") != null)
		{
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		else
		{
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Klant klant = null;
		HttpSession session = request.getSession();
		
		Map<String, String>fouten = new LinkedHashMap<>();
		
		if(request.getParameter("voornaam").isEmpty())
		{
			fouten.put("voornaam", "Voornaam niet ingevuld");
		}
		
		if(request.getParameter("familienaam").isEmpty())
		{
			fouten.put("familienaam", "Familienaam niet ingevuld");
		}
		
		if(request.getParameter("straat").isEmpty())
		{
			fouten.put("straat", "straat niet ingevuld");
		}
		
		if(request.getParameter("huisnr").isEmpty())
		{
			fouten.put("huisnr", "Huisnr. niet ingevuld");
		}
		
		if(request.getParameter("postcode").isEmpty())
		{
			fouten.put("postcode", "Postcode niet ingevuld");
		}
		
		if(request.getParameter("gemeente").isEmpty())
		{
			fouten.put("gemeente", "Gemeente niet ingevuld");
		}
		
		if(request.getParameter("gebruikersnaam").isEmpty())
		{
			fouten.put("gebruikersnaam", "Gebruikersnaam niet ingevuld");
		}
		
		if(request.getParameter("paswoord").isEmpty())
		{
			fouten.put("paswoord", "Paswoord niet ingevuld");
		}
		
		if(request.getParameter("herhaalpaswoord").isEmpty())
		{
			fouten.put("herhaalpaswoord", "Herhaal paswoord niet ingevuld");
		}
		
		if(! request.getParameter("paswoord").equals(request.getParameter("herhaalpaswoord")))
		{
			fouten.put("paswoordMatch", "Herhaal paswoord is niet gelijk met paswoord");
		}
		
		if(! request.getParameter("gebruikersnaam").isEmpty() && new KlantDAO().checkGebruikersnaamUniciteit(request.getParameter("gebruikersnaam")))
		{
			fouten.put("gebruikersnaamIngebruik", "Gebruikersnaam is reeds in gebruik");
		}
		
		if( ! fouten.isEmpty() )
		{
			request.setAttribute("fouten", fouten);
			request.setAttribute("firstname",request.getAttribute("voornaam"));
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		else
		{
			String voornaam = request.getParameter("voornaam");
			String familienaam = request.getParameter("familienaam");
			String straat = request.getParameter("straat");
			String huisNr = request.getParameter("huisnr");
			String postCode = request.getParameter("postcode");
			String gemeente = request.getParameter("gemeente");
			String gebruikersnaam = request.getParameter("gebruikersnaam");
			String paswoord = request.getParameter("paswoord");
			
			klant = new Klant(voornaam, familienaam, straat, huisNr, postCode, gemeente, gebruikersnaam, paswoord);
			new KlantDAO().createKlant(klant);
			session.setAttribute("klant", klant);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
			
		}
	}
}
