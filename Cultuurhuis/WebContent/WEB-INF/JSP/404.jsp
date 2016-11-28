<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<!doctype html>
<html lang='nl'>

<head>
	<c:import url="/WEB-INF/JSP/head.jsp">
		<c:param name='title' value="Pagina niet gevonden"/>
	</c:import>
</head>

<body>

 	<vdab:menu/>
	
	<h1>Pagina werd niet gevonden</h1>
	<img src=' ${contextPath}/images/error.png' alt='fout'>
	<p>De pagina die u wenst te bezoeken bestaat niet op onze website.</p>
	
</body>

</html>