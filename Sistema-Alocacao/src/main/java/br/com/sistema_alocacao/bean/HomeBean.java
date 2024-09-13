package br.com.sistema_alocacao.bean;

import br.com.sistema_alocacao.controller.TypeAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
