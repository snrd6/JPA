package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.GenresDAO;

@WebServlet(urlPatterns = "/index.htm", name = "indexservlet", loadOnStartup=1)

public class IndexServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final transient GenresDAO genreDAO = new GenresDAO();
	

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Iterable<String> genres = genreDAO.findAllGenres();
		request.setAttribute("genres", genres);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Resource(name = GenresDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) 
	{
		genreDAO.setDataSource(dataSource);
	}
	@Override
	public void init() throws ServletException 
	{

		ServletContext context = this.getServletContext();
		context.setAttribute("contextPath", context.getContextPath());
	}

}
