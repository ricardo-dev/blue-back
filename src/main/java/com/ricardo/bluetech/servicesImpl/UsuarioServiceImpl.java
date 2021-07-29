package com.ricardo.bluetech.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ricardo.bluetech.entities.Usuario;
import com.ricardo.bluetech.repositories.UsuarioRepository;
import com.ricardo.bluetech.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Optional<Usuario> buscarPorId(Long id) {
		return this.usuarioRepository.findById(id);
	}

	@Override
	public Usuario inserir(Usuario usuario) {
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> buscarPorEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.usuarioRepository.findByEmail(email));
	}

	@Override
	public Optional<List<Usuario>> getUsuarios() {
		// TODO Auto-generated method stub
		return Optional.ofNullable(this.usuarioRepository.findAll());
	}

}
