<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cliente Cadastrado</title>
</head>
<body>
	<h1>
		Cadastro realizado com sucesso obrigado
		<c:out value="${cliente.nomeCliente}" /> !!
	</h1>
	
</body>
</html>