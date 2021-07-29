package com.ricardo.bluetech.services;

import java.util.List;
import java.util.Optional;

import com.ricardo.bluetech.entities.Imagem;

public interface ImagemService {
	
	Optional<List<Imagem>> buscarPorImovelId(Long id);
	
	Imagem inserir(Imagem imagem);
}
