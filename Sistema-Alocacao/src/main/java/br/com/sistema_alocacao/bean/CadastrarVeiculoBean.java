package br.com.sistema_alocacao.bean;

import br.com.sistema_alocacao.controller.TypeAction;

import br.com.sistema_alocacao.dao.VeiculosDAO;

import br.com.sistema_alocacao.model.Veiculo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CadastrarVeiculoBean implements TypeAction {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public CadastrarVeiculoBean(HttpServletRequest req, HttpServletResponse resp) {

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
        RequestDispatcher reqDispatcher = req.getRequestDispatcher("/Veiculo-Cadastro.jsp");
        reqDispatcher.forward(req, resp);
    }

    private void processForm() throws ServletException, IOException {
        String marcaVeiculo = req.getParameter("marca-veiculo");
        String modeloVeiculo = req.getParameter("modelo-veiculo");
        String anoVeiculo = req.getParameter("ano-veiculo");
        Integer anoVeiculoLancado = Integer.valueOf(anoVeiculo);
        String corVeiculo = req.getParameter("cor-veiculo");
        String placaVeiculo = req.getParameter("placa-veiculo");

        if (marcaVeiculo == null || marcaVeiculo.trim().isEmpty()) {
            throw new RuntimeException("Marca inválida");
        }

        var veiculo = new Veiculo(marcaVeiculo,modeloVeiculo,anoVeiculoLancado,corVeiculo,placaVeiculo);
        var cadastrarVeiculo = new VeiculosDAO();

        cadastrarVeiculo.criarVeiculo(veiculo);
        req.setAttribute("veiculo", veiculo);

        RequestDispatcher reqDispatcher = req.getRequestDispatcher("/Veiculo-Cadastrado.jsp");
        reqDispatcher.forward(req, resp);

    }
    }

