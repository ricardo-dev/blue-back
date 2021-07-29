package com.ricardo.bluetech.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.bluetech.entities.Imagem;
import com.ricardo.bluetech.repositories.ImagemRepository;
import com.ricardo.bluetech.services.ImagemService;

@Service
public class ImagemServiceImpl implements ImagemService{
	
	@Autowired
	private ImagemRepository imagemRepository;

	@Override
	public Optional<List<Imagem>> buscarPorImovelId(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.imagemRepository.findByImovelId(id)) ;
	}

	@Override
	public Imagem inserir(Imagem imagem) {
		
		return this.imagemRepository.save(imagem);
	}

}
