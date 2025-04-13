package com.facol.projeto.dto;

import java.util.List;

import com.facol.projeto.model.Pauta;

public record AssociadoResponseDTO(Long id, String nome, List<Pauta> pautas) {

}
