<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cliente Cadastrado</title>
</head>
<body>
	<h1>
		Cadastro realizado com sucesso obrigado
		<c:out value="${cliente.nome}" /> !!
	</h1>
	<form action="controlador" method="get">
    	 	<input type="hidden" name="acao" value="cadastrarVeiculo">

            		<button type="submit">Cadastrar Veiculo</button>
    	</form> <br>
    	<form action="controlador" method="get">
            	 	<input type="hidden" name="acao" value="agendarVeiculo">

                    		<button type="submit">Reservar Veiculo</button>
            	</form>
	
</body>
</html>