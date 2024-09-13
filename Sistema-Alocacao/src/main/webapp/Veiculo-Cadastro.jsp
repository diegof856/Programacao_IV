<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastrar Veiculo</title>
</head>
<body>

	<h1>Insira as informacoes</h1>
<form action="controlador" method="post">
	 	<input type="hidden" name="acao" value="cadastrarVeiculo">
		Digite o nome da marca: <input type="text" name="marca-veiculo"  /> <br> <br>
		Digite o modelo: <input type="text" name="modelo-veiculo"><br> <br>
		Digite o ano do veiculo: <input type="text" name="ano-veiculo"  /> <br> <br>
		Digite a cor do veiculo: <input type="text" name="cor-veiculo"><br> <br>
		Digite a placa: <input type="text" name="placa-veiculo"><br> <br>

		<input type="submit" />
	</form>

</body>
</html>