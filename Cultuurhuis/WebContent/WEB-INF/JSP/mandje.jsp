<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>

<html lang='nl'>

<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name='title' value='Cultuurhuis' />
	</c:import>
</head>

<body>
	<div id="header">
		<h1>Het Cultuurhuis: Mandje<img src='images/mandje.png' alt='mandje'></h1>
		
	</div>
	
	<br><br>

	<nav>
		<ul class="zonderbolletjes">
			<c:url value="/index.htm" var="indexURL"/>
				<li><a href="${indexURL}">Voorstellingen</a></li>
			<c:url value="/zoekklant.htm" var="bevestigingURL"/>
				<li><a href="${bevestigingURL}">Bevestiging reservaties</a></li>
		</ul>
	</nav>
	
	<c:if test="${not empty sessionScope.mandje}">
	
		<c:url value="/mandje.htm" var="mandjeURL"/>
		
		<form action="${mandjeURL}" method="post" id="verwijderform">
			
				<table>
					<thead>
						<tr class="even">
							<th>Datum</th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Plaatsen</th><th><input type="submit" value="Verwijderen" id="verwijderknop"></th>
						</tr>
					</thead>
					
					<c:forEach var="reservatie" items="${mandjeItems}" varStatus="status" >
						<tbody>
							<tr class="${status.count % 2 == 0? 'even':'oneven'}">
								<td>${reservatie.voorstelling.datum}</td>
								<td><c:out value='${reservatie.voorstelling.titel}'/></td>
								<td><c:out value='${reservatie.voorstelling.uitvoerders}'/></td>
								<td>&euro;<fmt:formatNumber value='${reservatie.voorstelling.prijs}' groupingUsed='false' minFractionDigits="2"/></td>
								<td class="right">${reservatie.plaatsen}</td>
								<td class="center">
								<input type="checkbox" name="id" value="${reservatie.voorstelling.id}"/>
								</td>
							</tr>
						</tbody>
					</c:forEach>
				</table><br>
		</form>
		
		<script>
			document.getElementById('verwijderform').onsubmit = funtion()
			{
				document.getElementById('verwijderknop').disable = true;
			};
		</script>
		
		<h2>Te betalen: &euro;<fmt:formatNumber value='${totaalprijs}' groupingUsed='false' minFractionDigits="2"/> </h2>
			
			
	</c:if>

</body>

</html>