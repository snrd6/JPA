<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang='nl'>

<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name='title' value='Cultuurhuis' />
	</c:import>
</head>

<body>
	<div id="header">
		<h1>Het Cultuurhuis: Reserveren<img src='images/reserveren.png' alt='reserveren'></h1>
		
	</div>
	
	
	
	<nav>
		<ul>
			<c:url value="/index.htm" var="indexURL"/>
				<li><a href="${indexURL}">Voorstellingen</a></li>
		</ul>
	</nav>
			
	<dl>
		<dt>Voorstelling: </dt><dd><c:out value='${voorstelling.titel}'/></dd>
		<dt>Uitvoerders: </dt>
		<dd><c:out value='${voorstelling.uitvoerders}'/></dd>
		<dt>Datum: </dt>
		<dd><fmt:formatDate value='${voorstelling.datum}' type="both" dateStyle="short" timeStyle="short"/></dd>
		<dt>Prijs: </dt>

		<dd>&euro;<fmt:formatNumber value='${voorstelling.prijs}' groupingUsed='false' minFractionDigits="2"/></dd>
		<dt>Vrije plaatsen: </dt>
		<dd>${voorstelling.vrijePlaatsen}</dd>
	</dl>
	
			
	

	<c:url value="/reservatie.htm" var="reservatieURL">
		<c:param name="voorstellingId" value="${voorstelling.id}"/>
	</c:url>

	

	<form method="post" action="${reservatieURL}" id="reservatieform" >
		<label>Plaatsen: <span>${fouten.plaatsen}</span>
			<input name="plaatsen" type="number" value="${plaatsen}" maxLength="3" size="3" autofocus />
		</label>
		<br/>
		<input type="submit" value="Reserveren" id="reservatieknop"/>
	</form>
	
	
	<script>
		document.getElementById('reservatieform').onsubmit = funtion()
		{
			document.getElementById('reservatieknop').disable = true;
		};
	</script>
</body>

</html>