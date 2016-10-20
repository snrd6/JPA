package be.vdab.servlets.orders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.services.OrderService;


@WebServlet("/orders/unshippedorders.htm")
public class UnshippedOrdersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/orders/unshippedorders.jsp";   
	private static final String REDIRECT_URL = "index.htm";
	private final transient OrderService orderService=new OrderService();
	private static final int AANTAL_RIJEN = 15;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		@SuppressWarnings("unchecked")
		List<Long> noStockUnshippedOrdersIds = (List<Long>) request.getSession().getAttribute("noStockUnshippedOrders");
		
		@SuppressWarnings("unchecked")
		List<Long> shippedOrdersIds = (List<Long>) request.getSession().getAttribute("shippedOrders");
		
		
		if (noStockUnshippedOrdersIds != null) {
			List<Order> noStockUnshippedOrders = new ArrayList<>();
			for (long id : noStockUnshippedOrdersIds) {
				noStockUnshippedOrders.add(orderService.read(id));
			}
			request.setAttribute("noStockUnshippedOrders", noStockUnshippedOrders);
		}
		
		if (shippedOrdersIds != null) {
			request.setAttribute("shippedOrdersIds", shippedOrdersIds);
		}

		getUnshippedOrders(request, response);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	
	private void getUnshippedOrders(HttpServletRequest request, HttpServletResponse response) {
		int vanafRij = request.getParameter("vanafRij") == null ? 0
				: Integer.parseInt(request.getParameter("vanafRij"));
		request.setAttribute("vanafRij", vanafRij);
		request.setAttribute("aantalRijen", AANTAL_RIJEN);
		List<Order> unshippedorders = orderService.findAll(vanafRij, AANTAL_RIJEN + 1);
		if (unshippedorders.size() <= AANTAL_RIJEN) {
			request.setAttribute("laatstePagina", true);
		} else {
			unshippedorders.remove(AANTAL_RIJEN);
		}
		request.setAttribute("unshippedorders", unshippedorders);


	}	
	
	
	/*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			for (String order : request.getParameterValues("orderID")) {
				OrderService.setOrderAsShipped(Long.parseLong(order));
			}
			response.sendRedirect(response.encodeRedirectURL(REDIRECT_URL));
		} catch (NullPointerException ex) {
			response.sendRedirect(response.encodeRedirectURL(REDIRECT_URL));
	}*/

}
