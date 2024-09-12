package br.com.sistema_alocacao.controller;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/controlador")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("acao");
		String fqn;
		if (action != null) {
			fqn = "br.com.sistema_alocacao.bean." + Character.toUpperCase(action.charAt(0)) + action.substring(1)
					+ "Bean";
		} else {
			fqn = "br.com.sistema_alocacao.bean.HomeBean";
		
		}
		try {
			Class<?> classe = Class.forName(fqn);
			Constructor<?> construtor = classe.getDeclaredConstructor(HttpServletRequest.class,
					HttpServletResponse.class);

			TypeAction tipoAction = (TypeAction) construtor.newInstance(req, resp);
			tipoAction.execute();
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServletException(e);
		}
	}

}
