package br.com.sistema_alocacao.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.sistema_alocacao.model.Cliente;

public class ClienteDAO {

	private static List<Cliente> clientes = new ArrayList<>();

	static {
		Cliente conta1 = new Cliente("Diego", "Vitoria", "9999999", "diego@gmail.com");
		Cliente conta2 = new Cliente("Luis", "Feira Nova", "9999949", "luis@gmail.com");

		clientes.add(conta1);
		clientes.add(conta2);
	}

	public List<Cliente> listarClientes() {
		return ClienteDAO.clientes;
	}

	public void criarConta(Cliente novoCliente) {
		ClienteDAO.clientes.add(novoCliente);
	}
}
