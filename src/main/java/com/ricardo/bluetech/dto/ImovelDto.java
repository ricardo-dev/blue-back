package com.ricardo.bluetech.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.ricardo.bluetech.entities.Imagem;

public class ImovelDto {
	
	private Long id;
	
	@NotEmpty
	private String nomeImovel;
	
	private List<Imagem> imagem;
	
	private Long totalVotos;
	
	

	public Long getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(Long totalVotos) {
		this.totalVotos = totalVotos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeImovel() {
		return nomeImovel;
	}

	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public List<Imagem> getImagem() {
		return imagem;
	}

	public void setImagem(List<Imagem> imagem) {
		this.imagem = imagem;
	}
}
