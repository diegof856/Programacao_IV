package br.com.sistema_alocacao.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.sistema_alocacao.model.Veiculo;

public class VeiculosDAO {

	private static List<Veiculo> veiculos = new ArrayList<>();

	static {
		Veiculo veiculo1 = new Veiculo("Renault", "Sandero", 2014, "Rosa", "PKG-2151");
		Veiculo veiculo2 = new Veiculo("Chevrolet", "Celta", 2001, "Preto", "ADW-2554");

		veiculos.add(veiculo1);
		veiculos.add(veiculo2);
	}

	public List<Veiculo> listarVeiculos() {
		return VeiculosDAO.veiculos;
	}

	public void criarConta(Veiculo novoVeiculo) {
		VeiculosDAO.veiculos.add(novoVeiculo);
	}
	
	public LocalDate cadastradaData(LocalDate data) {
		return data;
	} 
	
}

