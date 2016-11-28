<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>

<html lang="nl">

<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name='title' value='Cultuurhuis' />
	</c:import>
</head>

<body>
<div id="header">
		<h1>Het Cultuurhuis:Nieuwe klant<img src='images/nieuweklant.png' alt='bevestig'></h1>
		
	</div>
	<nav>
		<ul class="menu">
			<c:url value="/index.htm" var="indexURL"/>
				<li><a href="${indexURL}">Voorstellingen</a></li>
			<c:url value="/mandje.htm" var="mandjeURL"/>
				<li><a href="${mandjeURL}">Reservatiemandje</a></li>
			<c:url value="/bevestiging.htm" var="bevestigingURL"/>
				<li><a href="${bevestigingURL}">Bevestiging reservaties</a></li>
		</ul>
	</nav>
	
	<c:url value="/nieuweklant.htm" var="nieuweklantURL"/>

	<form method="post" action="${nieuweklantURL}" id="nieuweKlant">
		<label>Voornaam:<span class='fout'>${badChars.badCharVn}</span>
			<input name= "voornaam" maxLength="50" size="50" value="${param.voornaam}" autofocus />
		</label>
		<label>Familienaam:<span class='fout'>${badChars.badCharFn}</span>
			<input name= "familienaam" maxLength="50" size="50" value="${param.familienaam}" />
		</label>
		<label>Straat:<span class='fout'>${badChars.badCharSt}</span>
			<input name= "straat" maxLength="50" size="50" value="${param.straat}" />
		</label>
		<label>Huisnr:<span class='fout'>${badChars.badCharHn}</span>
			<input name= "huisnr" maxLength="50" size="50" value="${param.huisnr}" />
		</label>
		<label>Postcode:<span class='fout'>${badChars.badCharPc}</span>
			<input name= "postcode" maxLength="50" size="50" value="${param.postcode}" />
		</label>
		<label>Gemeente:<span class='fout'>${badChars.badCharGm}</span>
			<input name= "gemeente" maxLength="50" size="50" value="${param.gemeente}" />
		</label>
		<label>Gebruikersnaam:<span class='fout'>${badChars.badChar}</span>
			<input name= "gebruikersnaam" maxLength="50" size="50" value="${param.gebruikersnaam}" />
		</label>
		<label>Paswoord:<span class='fout'>${badChars.badCharPw}</span>
			<input name= "paswoord" type="password" maxLength="50" size="50" value="${param.paswoord}" />
		</label>
		<label>Herhaal paswoord:<span class='fout'>${badChars.badCharHpw}</span>
			<input name= "herhaalpaswoord" type="password" maxLength="50" size="50"value="${param.herhaalpaswoord}" />
		</label>
			
		<input type="submit" value="OK" id="okKnop" />
	</form>
	
	<div>
		<ul class="blokjes">
			<c:forEach var="fout" items="${fouten}">
				<li>${fout.value}</li>
			</c:forEach>
		</ul>
	</div>
	
	<script>
		document.getElementById('nieuweKlant').onsubmit = funtion()
		{
			document.getElementById('okKnop').disable = true;
		};
	</script>
</body>

</html>