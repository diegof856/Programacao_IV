<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Veiculo Cadastrado</title>
</head>
<body>

	<h1>Cadastro realizado com sucesso </h1>
        		<h2>Marca: <c:out value="${veiculo.marca}" /></h2>
    <h2>Modelo: <c:out value="${veiculo.modelo}" /></h2>
     <h2>Ano: <c:out value="${veiculo.ano}" /></h2>
     <h2>Cor: <c:out value="${veiculo.cor}" /></h2>
      <h2>Placa: <c:out value="${veiculo.placa}" /></h2>
<form action="controlador" method="get">
		<input type="hidden" name="acao" value="bookCar">
		<button type="submit">Reservar Veiculo</button>
	</form>

</body>
</html>