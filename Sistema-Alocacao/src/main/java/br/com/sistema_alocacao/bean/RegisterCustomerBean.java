package br.com.sistema_alocacao.bean;

import java.io.IOException;

import br.com.sistema_alocacao.controller.TypeAction;
import br.com.sistema_alocacao.dao.ClienteDAO;
import br.com.sistema_alocacao.model.Cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterCustomerBean implements TypeAction {

	private HttpServletRequest req;
	private HttpServletResponse resp;

	public RegisterCustomerBean(HttpServletRequest req, HttpServletResponse resp) {
		
		this.req = req;
		this.resp = resp;
	}
	@Override
	public void execute() throws ServletException, IOException {
		
	        
		if ("POST".equalsIgnoreCase(req.getMethod())) {
            // Processar o envio do formulário
            processForm();
        } else {
            // Exibir o formulário
            showForm();
        }
		
	}
	 private void showForm() throws ServletException, IOException {
	        RequestDispatcher reqDispatcher = req.getRequestDispatcher("/RegisterCustomer.jsp");
	        reqDispatcher.forward(req, resp);
	    }

	    private void processForm() throws ServletException, IOException {
	        String nomeCliente = req.getParameter("nome-cliente");
	        String enderecoCliente = req.getParameter("endereco-cliente");
	        String telefoneConta = req.getParameter("telefone-cliente");
	        String emailCliente = req.getParameter("email-cliente");

	        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
	            throw new RuntimeException("Nome do cliente inválido");
	        }

	        var cliente = new Cliente(nomeCliente, enderecoCliente, telefoneConta, emailCliente);
	        var cadastrarCliente = new ClienteDAO();

	        cadastrarCliente.criarConta(cliente);
	        req.setAttribute("cliente", cliente);

	        RequestDispatcher reqDispatcher = req.getRequestDispatcher("/Cliente-Cadastrado.jsp");
	        reqDispatcher.forward(req, resp);
	       
	    }
}
