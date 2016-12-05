<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang='nl'>

<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name='title' value='Cultuurhuis' />
	</c:import>
</head>

<body>

	<div id="header">
		<h1>Het Cultuurhuis : Bevestiging reservaties<img src='images/bevestig.png' alt='bevestig'></h1>
		
	</div>
	
	<nav>
		<ul class="menu">
			<c:url value="/index.htm" var="indexURL"/><li><a href="${indexURL}">Voorstellingen</a></li>
			<c:url value="/mandje.htm" var="mandjeURL"/><li><a href="${mandjeURL}">Reservatiemandje</a></li>
		</ul>
	</nav>
		
	<h3>Stap 1:wie ben je ?</h3>
	<br>
	<c:url value="/zoekklant.htm" var="zoekKlantURL"/>
	
	<form method="post" action="${zoekKlantURL}" style="text-align:left">
		<label>Gebruikersnaam:<span class='fout'></span>
			<input name= "gebruikersnaam" value="${klant}" maxLength="20" size="20" autofocus <c:if test="${ not empty klant}">disabled</c:if>/></label><br/>
		<label>Paswoord:<span class='fout'></span>
			<input type="password" name= "paswoord" maxLength="15" size="15" <c:if test="${not empty klant}">disabled</c:if>/><br/>
			<input type="submit" value="Zoek me op" <c:if test="${not empty klant}">disabled</c:if>/></label><br/>
	</form>
	
	<c:url value="/nieuweklant.htm" var="nieuweklantURL"/>
	
	<form method="get" action="${nieuweklantURL}">
		<input type="submit" value="Ik ben nieuw" <c:if test="${not empty klant}">disabled</c:if>/>
	</form>
	
	<section><span class="fout">${fouten.verkeerd}</span></section>
	${klantgegevens} 
	<h3>Stap 2:Bevestigen</h3>
	<br>
	<c:url value="/boeking.htm" var="boekingURL"/>
	
	<form method="get" action="${boekingURL}" id="bevestigingForm">
		<input type="submit" value="Bevestigen" id="bevestigingKnop" <c:if test="${empty klant}">disabled</c:if>/>
	</form>
	
	<script>
		document.getElementById('bevestigingForm').onsubmit = funtion()
		{
			document.getElementById('bevestigingKnop').disable = true;
		};
	</script>
</body>

</html>