package com.ricardo.bluetech.services;

import java.util.List;
import java.util.Optional;

import com.ricardo.bluetech.entities.Imovel;

public interface ImovelService {
	
	Optional<Imovel> buscarPorId(Long id);
	
	Optional<List<Imovel>> buscarTodos();
	
	Imovel inserir(Imovel imovel);
}
