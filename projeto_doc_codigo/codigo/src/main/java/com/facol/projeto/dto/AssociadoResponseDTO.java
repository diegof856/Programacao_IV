package com.facol.projeto.dto;

import java.util.List;

public record AssociadoResponseDTO(Long id, String nome, List<PautaResponseDTO> pautas) {

}
