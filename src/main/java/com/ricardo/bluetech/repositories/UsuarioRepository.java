package com.ricardo.bluetech.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ricardo.bluetech.entities.Usuario;

@Transactional(readOnly = true)
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findByNome(String nome);
	
	Usuario findByEmail(String email);
}
