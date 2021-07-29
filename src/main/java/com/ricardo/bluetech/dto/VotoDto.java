package com.ricardo.bluetech.dto;

import java.util.List;

public class VotoDto {
	
	private Long idImovel;
	
	private Long idUsuario;
	
	private Long totalVotos;
	
	private String nomeImovel;
	
	private Long totalVotosImovel;
	
	private List<String> nomeUsuario;
	
	public Long getIdImovel() {
		return idImovel;
	}

	public void setIdImovel(Long idImovel) {
		this.idImovel = idImovel;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(Long totalVotos) {
		this.totalVotos = totalVotos;
	}

	public String getNomeImovel() {
		return nomeImovel;
	}

	public void setNomeImovel(String nomeImovel) {
		this.nomeImovel = nomeImovel;
	}

	public Long getTotalVotosImovel() {
		return totalVotosImovel;
	}

	public void setTotalVotosImovel(Long totalVotosImovel) {
		this.totalVotosImovel = totalVotosImovel;
	}

	public List<String> getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(List<String> nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	
}
