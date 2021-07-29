package com.ricardo.bluetech.securitys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ricardo.bluetech.entities.Usuario;
import com.ricardo.bluetech.enums.UsuarioEnum;



public class JwtUserFactory {
	
	private JwtUserFactory() {
		
	}
	
	public static JwtUser create(Usuario pessoa) {
		return new JwtUser(
				pessoa.getId(), pessoa.getEmail(), pessoa.getSenha(),
				mapToGrantedAuthorities(pessoa.getTipoEnum())
				);
	}
	
	public static List<GrantedAuthority> mapToGrantedAuthorities(UsuarioEnum perfilEnum){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));
		return authorities;
	}

}

