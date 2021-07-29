package com.ricardo.bluetech.services;

import java.util.List;
import java.util.Optional;

import com.ricardo.bluetech.entities.Usuario;

public interface UsuarioService {
	
	Optional<Usuario> buscarPorId(Long id);
	
	Optional<Usuario> buscarPorEmail(String email);
	
	Optional<List<Usuario>> getUsuarios();
	
	Usuario inserir(Usuario usuario);
}
