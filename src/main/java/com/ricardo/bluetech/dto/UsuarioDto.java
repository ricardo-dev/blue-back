package com.ricardo.bluetech.dto;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;



public class UsuarioDto {
	
	private Long id;
	
	private String nome;
	
	@Email(message="Email digitado inválido!")
	@NotEmpty
	private String email;
	
	private Optional<String> senha = Optional.empty();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Optional<String> getSenha() {
		return senha;
	}

	public void setSenha(Optional<String> senha) {
		this.senha = senha;
	}
}
