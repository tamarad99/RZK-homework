<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pretraga dogadjaja</title>
</head>
<body>

<c:if test="${empty events}">
	<form action="/PlanerWEB/SearchEventServlet" method="get">
		Datum: <input type="date" name="fromDate">
		<input type="submit" value="search">
	</form>
</c:if>
	<c:if test="${! empty events}">
	Dogadjaji za ${fromDate}: <br><br>
	<table border="1">
		<tr>
			<th>Dogadjaj</th>
			<th>Pocetak</th>
			<th>Kraj</th>
		</tr>
		<c:forEach items="${events}" var="e">
		<fmt:parseDate value="${e.fromDate}" pattern="yyyy-MM-dd'T'HH:mm" var="fromTime" type="both"></fmt:parseDate>
		<fmt:parseDate value="${e.toDate}" pattern="yyyy-MM-dd'T'HH:mm" var="toTime" type="both"></fmt:parseDate>
			<tr>
				<td>${e.eventType.name}</td>
				<td><fmt:formatDate pattern="HH:mm" value="${fromTime}" /></td>
				<td><fmt:formatDate pattern="HH:mm" value="${toTime}" /></td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>