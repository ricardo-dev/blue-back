package com.ricardo.bluetech.repositories;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ricardo.bluetech.entities.Imagem;
import com.ricardo.bluetech.entities.Imovel;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ImagemRepositoryTest {
	
	@Autowired
	private ImovelRepository iR;
	
	@Autowired
	private ImagemRepository imR;
	
	public static Long idImovel;
	
	@Before
	public void setUp() {
		Imovel i = new Imovel();
		i.setNomeImovel("Hotel ABC");
		
		i = this.iR.save(i);
		
		this.idImovel = i.getId();
		
		Imagem im = new Imagem();
		im.setImagemPath("caminho da foto");
		im.setImovel(i);
		
		Imagem im2 = new Imagem();
		im2.setImagemPath("Caminho da foto 2");
		im2.setImovel(i);
		
		this.imR.save(im);
		this.imR.save(im2);
	}
	
	@After
	public void tearDown() {
		this.iR.deleteAll();
	}
	
	@Test
	public void teste() {
		List<Imagem> li = this.imR.findByImovelId(this.idImovel);
		assertEquals(2, li.size());
	}

}
