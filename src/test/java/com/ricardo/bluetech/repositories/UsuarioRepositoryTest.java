package com.ricardo.bluetech.repositories;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ricardo.bluetech.entities.Usuario;
import com.ricardo.bluetech.enums.UsuarioEnum;
import com.ricardo.bluetech.utils.PasswordUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	
	@Autowired
	public UsuarioRepository usuarioRepository;
	
	@Before
	public void setUp() {
		Usuario usuario = new Usuario();
		usuario.setNome("Ricardo");
		usuario.setEmail("ricardoengdepc@gmail.com");
		usuario.setSenha(PasswordUtils.gerarBCrypt("12345"));
		usuario.setTipoEnum(UsuarioEnum.ROLE_USUARIO);
		
		this.usuarioRepository.save(usuario);
	}
	
	@After
	public void tearDown() {
		this.usuarioRepository.deleteAll();
	}
	
	@Test
	public void inserirUsuario() {
		Usuario u = this.usuarioRepository.findByNome("Ricardo");
		assertNotNull(u);
	}
}
