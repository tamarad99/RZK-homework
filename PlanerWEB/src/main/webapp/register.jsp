<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="/PlanerWEB/RegisterServlet" method="post">
		Ime: <input type="text" name="firstname" id="firstname"><br>
		Prezime: <input type="text" name="lastname" id="lastname"><br>
		email: <input type="text" name="email" id="email"><br>
		Password: <input type="password" name="password" id="password"><br>
		<input type="submit" value="Register"><br>
	</form>

</body>
</html>