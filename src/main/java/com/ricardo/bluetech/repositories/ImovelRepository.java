package com.ricardo.bluetech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.bluetech.entities.Imovel;

@Transactional(readOnly=true)
public interface ImovelRepository extends JpaRepository<Imovel, Long>{

	Imovel findByNomeImovel(String nomeImovel);
	
}
