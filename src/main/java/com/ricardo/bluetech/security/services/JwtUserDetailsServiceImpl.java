package com.ricardo.bluetech.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ricardo.bluetech.entities.Usuario;
import com.ricardo.bluetech.repositories.UsuarioRepository;
import com.ricardo.bluetech.securitys.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository pessoaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario pessoa = pessoaRepository.findByEmail(username);

		if (pessoa != null) {
			return JwtUserFactory.create(pessoa);
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}

}