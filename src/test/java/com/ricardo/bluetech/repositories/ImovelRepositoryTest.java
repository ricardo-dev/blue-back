package com.ricardo.bluetech.repositories;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ricardo.bluetech.entities.Imovel;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ImovelRepositoryTest {
	
	@Autowired
	private ImovelRepository imovelRepository;
	
	@Before
	public void setUp() {
		Imovel imovel = new Imovel();
		imovel.setNomeImovel("Hotel xyz");
		
		this.imovelRepository.save(imovel);
	}
	
	@After
	public void tearDown() {
		this.imovelRepository.deleteAll();
	}
	
	@Test
	public void inserirImovel() {
		Imovel i = this.imovelRepository.findByNomeImovel("Hotel xyz");
		Assert.assertNotNull(i);
	}
}
