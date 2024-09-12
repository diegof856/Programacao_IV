package br.com.sistema_alocacao.bean;

import java.io.IOException;

import br.com.sistema_alocacao.controller.TypeAction;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class HomeBean implements TypeAction {

    
	private HttpServletRequest req;
	private HttpServletResponse resp;
	
    
    public HomeBean(HttpServletRequest req, HttpServletResponse resp) {
       this.req = req;
       this.resp = resp;
    }


	@Override
	public void execute() throws ServletException, IOException {
		
		RequestDispatcher reqDispatcher = req.getRequestDispatcher("/ScreenIncial.jsp");
		reqDispatcher.forward(req, resp);
	}

	
}
