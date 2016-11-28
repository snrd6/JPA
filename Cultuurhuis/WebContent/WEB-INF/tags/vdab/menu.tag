<%@ tag description='menu' pageEncoding='UTF-8'%>

<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>

<header>
	<nav>
		<ul>
			<c:forEach var='genre' items='${genres}'>
				<c:url value="/voorstelling.htm" var="voorstellingURL">
					<c:param name="genre" value="${genre}" />
				</c:url>
				<li><a href="${voorstellingURL}">${genre}</a></li>
			</c:forEach>
		</ul>
	</nav>
</header>