<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!DOCTYPE html>

<html lang='nl'>

<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name='title' value='Cultuurhuis' />
	</c:import>
</head>

<body>
	 <div id="header">
		<h1>Het Cultuurhuis: Voorstellingen</h1>
		<img src='images/voorstellingen.png' alt='voorstellingen'>
	</div>
	
	<br><br>
	
	<h2>Genres</h2>

 	<vdab:menu/>
	
	<br><br>
	
	<c:if test="${not empty sessionScope.mandje}">
		<ul class="menu">
			<c:url value="/mandje.htm" var="mandjeURL"/><li><a href="${mandjeURL}">Reservatiemandje</a></li>
			<c:url value="/zoekklant.htm" var="bevestigingURL"/><li><a href="${bevestigingURL}">Bevestiging reservaties</a><li>
		</ul>
	</c:if>

	

	<h2>${genre} voorstellingen</h2>

	<c:if test="${not empty voorstellingen}">
	    <table >
			<thead>
				<tr class="even">
					<th>Datum</th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Vrije plaatsen</th><th>Reserveren</th>
				</tr>
			</thead>
			
			<c:forEach var="voorstelling" items="${voorstellingen}" varStatus="status">
				<tbody>
					<tr class="${status.count % 2 == 0? 'even':'oneven'}">
						<td><fmt:formatDate value='${voorstelling.datum}' type="both" dateStyle="short" timeStyle="short"/></td>
						<td><c:out value='${voorstelling.titel}'/></td>
						<td><c:out value='${voorstelling.uitvoerders}'/></td>
						<td>&euro;<fmt:formatNumber value='${voorstelling.prijs}' groupingUsed='false' minFractionDigits="2"/></td>
						<td>${voorstelling.vrijePlaatsen}</td>
						<td>
							<c:if test="${voorstelling.vrijePlaatsen > 0}">
							
								<c:url value="/reservatie.htm" var ="reservatieURL">
									<c:param name="voorstellingId" value="${voorstelling.id}"/>
								</c:url>
								<a href="<c:out value='${reservatieURL}'/>">Reservatie</a>
							</c:if>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</c:if>
	<br></br>
		<br></br>
			<br></br>
				<br></br>
	<div id="bottom">
	
	</div >
</body>

</html>
