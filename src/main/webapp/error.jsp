<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
</head>
<body>
	<h2 style="text-align: center">
		<%
		String error = (String) session.getAttribute("error");
		out.println("Erreur: " + error);
		%>
	</h2>
</body>
</html>
