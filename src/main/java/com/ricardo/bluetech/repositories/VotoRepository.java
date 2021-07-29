package com.ricardo.bluetech.repositories;

import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.bluetech.entities.Voto;

@Transactional(readOnly=true)
@NamedQueries({
	@NamedQuery(name="VotoRepository.findByImovelId", query="SELECT v FROM voto v WHERE v.imovelId:=id"),
	//@NamedQuery(name="", query=""),
	//@NamedQuery(name="", query=""),
})
public interface VotoRepository extends JpaRepository<Voto, Long>{
	
	List<Voto> findByImovelId(@Param("id") Long id);
	
	Voto findByUsuarioId(Long id);
}
