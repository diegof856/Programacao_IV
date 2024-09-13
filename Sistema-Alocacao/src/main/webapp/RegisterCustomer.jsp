<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar Cliente</title>
</head>
<body>
	<form action="controlador" method="post">
	 	<input type="hidden" name="acao" value="registerCustomer">
		Digite seu nome: <input type="text" name="nome-cliente"  /> <br> <br>
		Digite seu endereco: <input type="text" name="endereco-cliente"><br> <br>
		Digite seu telefone: <input type="text" name="telefone-cliente"  /> <br> <br>
		Digite seu email: <input type="email" name="email-cliente"><br> <br>
		<input type="submit" />
	</form>
</body>
</html>