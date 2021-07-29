package com.ricardo.bluetech.security.dto;

public class TokenDto {

	private String token; 
	private Long id;
	private String nome;
	private String email;
	
	public TokenDto(String token, Long id, String nome, String email) {
		this.token = token;
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public TokenDto(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
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
	
	

}
