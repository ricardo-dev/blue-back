package com.ricardo.bluetech.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="imovel")
public class Imovel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Column(nullable=false)
	public String nomeImovel;
	
	@OneToMany(mappedBy="imovel", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Imagem> fotosImovel;

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

	public List<Imagem> getFotosImovel() {
		return fotosImovel;
	}

	public void setFotosImovel(List<Imagem> fotosImovel) {
		this.fotosImovel = fotosImovel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
