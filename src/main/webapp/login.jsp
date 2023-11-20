<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Connexion</title>
</head>
<body>
	<h1 style="text-align: center">Log in</h1>
	<form method="post" action="login">
		<table style="margin-left: auto; margin-right: auto">
			<tr>
				<td>Nom d'utilisateur:</td>
				<td><input type="text" name="username" required></td>
			</tr>
			<tr>
				<td>Mot de passe:</td>
				<td><input type="password" name="password" required></td>
			</tr>
			<tr>
				<td><input type="submit" value="Log in"></td>
			</tr>
		</table>
	</form>
</body>
</html>
