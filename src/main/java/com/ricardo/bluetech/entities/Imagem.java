package com.ricardo.bluetech.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="imagem")
public class Imagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Column(nullable=false)
	public String imagemPath;
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Imovel imovel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImagemPath() {
		return imagemPath;
	}

	public void setImagemPath(String imagemPath) {
		this.imagemPath = imagemPath;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
