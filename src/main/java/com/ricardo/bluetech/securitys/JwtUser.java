package com.ricardo.bluetech.securitys;

import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails{
	
	private static long serialVersionUID = -268046329085485932L;
	
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

/*
 * private User getUserByToken() {
		JwtUser principal = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Principal "+principal.getUsername());
		Optional<User> logg = this.userRepository.findByEmail(principal.getUsername());
		if(!logg.isPresent()) {
			throw new TokenNotFoundException();
		}
		return logg.get();
	}
 */