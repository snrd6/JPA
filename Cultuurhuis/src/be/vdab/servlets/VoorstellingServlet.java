package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.GenresDAO;
import be.vdab.dao.VoorstellingenDAO;
import be.vdab.entities.Voorstelling;

@WebServlet(urlPatterns = "/voorstelling.htm", name = "voorstellingservlet")

public class VoorstellingServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/voorstelling.jsp";
	
	private final transient VoorstellingenDAO voorstellingDAO = new VoorstellingenDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		        
        Iterable<String>genres = new GenresDAO().findAllGenres();
        request.setAttribute("genres", genres);
        
      
      
        String genre = request.getParameter("genre");

        Iterable<Voorstelling> voorstellingen = voorstellingDAO.findVoorstellingenByGenre(genre);
        request.setAttribute("voorstellingen", voorstellingen);
        request.setAttribute("genre", genre);

        request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
