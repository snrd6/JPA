<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE HTML>

<html lang='nl'>

<head>

	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name='title' value='Cultuurhuis' />
	</c:import>

</head>

<body>
 	<div id="header">
		<h1>Het Cultuurhuis</h1>
		<img src='images/voorstellingen.png' alt='voorstellingen'>
		
	</div>
	
	<br><br>
	
	<h2>Genres</h2>

 	<vdab:menu/>
	
	<c:if test="${not empty sessionScope.mandje}">
	<nav>
		<ul>
			<c:url value="/mandje.htm" var="mandjeURL"/><li>
			<a href="${mandjeURL}">Reservatiemandje</a></li>
			<c:url value="/bevestiging.htm" var="bevestigingURL"/><li>
			<a href="${bevestigingURL}">Bevestiging reservaties</a><li>
		</ul>
	</nav>
	</c:if>
	
  

</body>

</html>
