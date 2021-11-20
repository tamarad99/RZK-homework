<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"
  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create event</title>
</head>
<body>
Create Event:

	<form action="/PlanerWEB/CreateEventServlet" method="post">
		Description: <input type="text" name="description"><br>
		Beggining date: <input type="datetime" name="fromDate"><br>
		Ending date: <input type="datetime" name="toDate"><br>
		Event type: <select name="eventType">
						<c:forEach var="eventType" items="${eventTypes}">
							<option value="${eventType.id}">"${eventType.name}"</option>
						</c:forEach>
					</select><br>
					
		<input type="submit" value="Add">
	</from>

</body>
</html>