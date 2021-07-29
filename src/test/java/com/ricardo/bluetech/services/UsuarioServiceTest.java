package com.ricardo.bluetech.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ricardo.bluetech.entities.Usuario;
import com.ricardo.bluetech.repositories.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioServiceTest {
	
	@MockBean
	private UsuarioRepository usuarioRepository;
	
	@Before
	public void setUp() throws Exception{
		BDDMockito.given(
				this.usuarioRepository.save(Mockito.any(Usuario.class))
				).willReturn(new Usuario());
		BDDMockito.given(
				this.usuarioRepository.findByNome(Mockito.anyString())
				).willReturn(new Usuario());
	}
	
	@Test
	public void teste1() {
		
	}

}
