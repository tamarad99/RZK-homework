<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="/PlanerWEB/LoginServlet" method="post">
		Email: <input type="text" name="email" id="email"><br>
		Password: <input type="password" name="password" id="password"><br>
		<input type="submit" value="Login"><br>
	</form>
	
	Ukoliko nemate nalog mozete se registrovati klikom na <a href="register.jsp">link.</a>

</body>
</html>