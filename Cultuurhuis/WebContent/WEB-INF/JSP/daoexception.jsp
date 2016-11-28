<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<c:set value="${pageContext.servletContext.contextPath}" var="contextURL"/>

<html lang="nl">
	
	<head>
		<c:import url="/WEB-INF/JSP/head.jsp">
			<c:param name='title' value='Cultuurhuis - Foutboodschap' />
		</c:import>
	</head>
	
	<body>
	
		<c:url value="/index.htm" var="indexURL"/>
		
		<div id="header">
			<h1>Het Cultuurhuis</h1>
			<img src='images/voorstellingen.png' alt='voorstellingen'>
		</div>
		
		<br><br>
		
		<nav>
			<ul>
				<c:url value="/index.htm" var="indexURL"/>
				<li><a href="${indexURL}">Voorstellingen</a></li>
			</ul>
		</nav>
		
		<h1>Problemen bij het ophalen van de gegevens</h1>
		<img alt="data fout" src="${contextURL}/images/datafout.jpg">
		
		<p>
			Technische storing. Gelieve plat op de grond te gaan liggen en wacht op hulp.<br/>
		</p>
		
	</body>

</html>