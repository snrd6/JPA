<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<nav>
	<ul>
		<li><a href="#">Orders</a>
			<ul>
				<li><a href="<c:url value='/orders/unshippedorders.htm'/>">Unshipped orders</a></li>
				
			</ul></li>
		<li><a href="#">Contact</a>
			<ul>
				<li><a href="<c:url value='/gegevens/contact.htm'/>">Contactinformatie</a></li>
			</ul></li>
		
	</ul>
</nav>