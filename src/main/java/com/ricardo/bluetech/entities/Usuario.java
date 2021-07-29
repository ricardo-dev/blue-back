package com.ricardo.bluetech.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ricardo.bluetech.enums.UsuarioEnum;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Enumerated(EnumType.STRING)
	public UsuarioEnum tipoEnum;
	
	public String nome;
	
	@Column(nullable=false)
	public String email;
	
	@Column(nullable=false)
	public String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UsuarioEnum getTipoEnum() {
		return tipoEnum;
	}

	public void setTipoEnum(UsuarioEnum tipoEnum) {
		this.tipoEnum = tipoEnum;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
