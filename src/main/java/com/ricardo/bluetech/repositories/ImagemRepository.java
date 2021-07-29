package com.ricardo.bluetech.repositories;

import java.util.List;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.bluetech.entities.Imagem;

@Transactional(readOnly = true)
@NamedQueries({
	@NamedQuery(name="ImagemRepository.findByImovelId",
				query="SELECT i FROM imagem i WHERE i.imovel.id:=id")
})
public interface ImagemRepository extends JpaRepository<Imagem, Long> {
	
	List<Imagem> findByImovelId(@Param("id") Long id);

}
