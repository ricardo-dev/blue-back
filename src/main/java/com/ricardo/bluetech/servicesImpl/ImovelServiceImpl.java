package com.ricardo.bluetech.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.bluetech.entities.Imovel;
import com.ricardo.bluetech.repositories.ImovelRepository;
import com.ricardo.bluetech.services.ImovelService;

@Service
public class ImovelServiceImpl implements ImovelService{
	
	@Autowired
	private ImovelRepository imovelRepository;

	@Override
	public Optional<Imovel> buscarPorId(Long id) {
		return this.imovelRepository.findById(id);
	}

	@Override
	public Imovel inserir(Imovel imovel) {
		return this.imovelRepository.save(imovel);
	}

	@Override
	public Optional<List<Imovel>> buscarTodos() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.imovelRepository.findAll());
	}

}
