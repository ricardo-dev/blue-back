package com.ricardo.bluetech;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ricardo.bluetech.entities.Imagem;
import com.ricardo.bluetech.entities.Imovel;
import com.ricardo.bluetech.services.ImagemService;
import com.ricardo.bluetech.services.ImovelService;

@SpringBootApplication
public class BlueTechApplication {
	
	@Autowired
	private ImovelService imovelService;
	
	@Autowired
	private ImagemService imagemService;

	public static void main(String[] args) {
		SpringApplication.run(BlueTechApplication.class, args);
		
	}
	
	public void setUp(String nome) {
		Imovel imovel = new Imovel();
		imovel.setNomeImovel(nome);
		imovel = this.imovelService.inserir(imovel);
		
		Imagem img1 = new Imagem();
		img1.setImagemPath("https://s3.amazonaws.com/imagem-ilumino-comercio/photos/user.png");
		img1.setImovel(imovel);
		this.imagemService.inserir(img1);
		
		Imagem img2 = new Imagem();
		img2.setImagemPath("https://s3.amazonaws.com/imagem-ilumino-comercio/photos/user.png");
		img2.setImovel(imovel);
		this.imagemService.inserir(img2);
	}
	
}
