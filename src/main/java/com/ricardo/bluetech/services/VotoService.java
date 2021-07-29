package com.ricardo.bluetech.services;

import java.util.List;
import java.util.Optional;

import com.ricardo.bluetech.entities.Voto;

public interface VotoService {
	
	Optional<List<Voto>> buscarTodos();
	
	Optional<List<Voto>> buscarPorIdImovel(Long id);
	
	Voto inserirVoto(Voto voto);
	
	Optional<Voto> buscarPorIdUsuario(Long id);
}
