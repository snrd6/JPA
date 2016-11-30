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
		
		String voornaam = request.getParameter("voornaam");
		if( voornaam==null||voornaam.trim().isEmpty())
		{
			fouten.put("voornaam", "Voornaam niet ingevuld");
		}
		
		String familienaam=request.getParameter("familienaam");
		if(familienaam==null||familienaam.trim().isEmpty())
		{
			fouten.put("familienaam", "Familienaam niet ingevuld");
		}
		
		String straat=request.getParameter("straat");
		if(straat==null||straat.trim().isEmpty())
		{
			fouten.put("straat", "straat niet ingevuld");
		}
		
		String huisnr=request.getParameter("huisnr");
		if(huisnr==null||huisnr.trim().isEmpty())
		{
			fouten.put("huisnr", "Huisnr. niet ingevuld");
		}
		
		String postcode=request.getParameter("postcode");
		if(postcode==null||postcode.trim().isEmpty())
		{
			fouten.put("postcode", "Postcode niet ingevuld");
		}
		
		String gemeente=request	.getParameter("gemeente");
		if(gemeente==null||gemeente.trim().isEmpty())
		{
			fouten.put("gemeente", "Gemeente niet ingevuld");
		}
		
		String gebruikersnaam=request.getParameter("gebruikersnaam");
		if(gebruikersnaam==null||gebruikersnaam.trim().isEmpty())
		{
			fouten.put("gebruikersnaam", "Gebruikersnaam niet ingevuld");
		}
		
		String paswoord=request.getParameter("paswoord");
		if(paswoord==null||paswoord.trim().isEmpty())
		{
			fouten.put("paswoord", "Paswoord niet ingevuld");
		}
		
		String herhaalpaswoord=request.getParameter("herhaalpaswoord").trim();
		if(herhaalpaswoord==null||herhaalpaswoord.trim().isEmpty())
		{
			fouten.put("herhaalpaswoord", "Herhaal paswoord niet ingevuld");
		}
		
		
		if(! request.getParameter("paswoord").equals(request.getParameter("herhaalpaswoord")))
		{
			fouten.put("paswoordMatch", "Herhaal paswoord is niet gelijk met paswoord");
		}
		
		if(! request.getParameter("gebruikersnaam").isEmpty() && new KlantDAO().checkGebruikersnaamUniciteit(request.getParameter("gebruikersnaam"),request.getParameter("paswoord")))
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
			
			
			klant = new Klant(voornaam, familienaam, straat, huisnr, postcode, gemeente, gebruikersnaam, paswoord);
			new KlantDAO().createKlant(klant);
			session.setAttribute("klant", klant);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
			
		}
	}
}
