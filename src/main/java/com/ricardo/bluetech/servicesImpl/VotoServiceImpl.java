package com.ricardo.bluetech.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.bluetech.entities.Voto;
import com.ricardo.bluetech.repositories.VotoRepository;
import com.ricardo.bluetech.services.VotoService;

@Service
public class VotoServiceImpl implements VotoService{
	
	@Autowired
	private VotoRepository votoRepository;

	@Override
	public Optional<List<Voto>> buscarTodos() {
		return Optional.ofNullable(this.votoRepository.findAll());
	}

	@Override
	public Optional<List<Voto>> buscarPorIdImovel(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.votoRepository.findByImovelId(id));
	}

	@Override
	public Voto inserirVoto(Voto voto) {
		// TODO Auto-generated method stub
		return votoRepository.save(voto);
	}

	@Override
	public Optional<Voto> buscarPorIdUsuario(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.votoRepository.findByUsuarioId(id));
	}
}
