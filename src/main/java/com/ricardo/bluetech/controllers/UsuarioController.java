package com.ricardo.bluetech.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.bluetech.dto.UsuarioDto;
import com.ricardo.bluetech.entities.Usuario;
import com.ricardo.bluetech.enums.UsuarioEnum;
import com.ricardo.bluetech.response.Response;
import com.ricardo.bluetech.services.UsuarioService;
import com.ricardo.bluetech.utils.PasswordUtils;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/cadastrar-usuario/")
	public ResponseEntity<Response<UsuarioDto>> inserirUsuario(
			@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result
			){
		Response response = new Response();
		
		log.info("Entrada no método!");
		Optional<Usuario> usuario = this.usuarioService.buscarPorEmail(usuarioDto.getEmail());
		if(usuario.isPresent()) {
			result.addError(new ObjectError("Usuario", "email já cadastrado!"));
		}
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(
					error -> response.getErrors().add(error.getDefaultMessage())
					);
			return ResponseEntity.badRequest().body(response);
		}
		
		Usuario usuario2 = this.converterDtoParaUsuario(usuarioDto);
		this.usuarioService.inserir(usuario2);
		response.setData(this.converterUsuarioParaDto(usuario2));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/listar-todos-usuarios/")
	public ResponseEntity<Response<List<UsuarioDto>>> listarUsuarios(){
		Response response = new Response();
		
		Optional<List<Usuario>> listaUsuario = this.usuarioService.getUsuarios();
		if(!listaUsuario.isPresent()) {
			response.getErrors().add("Sem usuarios cadastrados!");
			return ResponseEntity.badRequest().body(response);
		}
		
		List<UsuarioDto> listaUsuarioDto = new ArrayList<UsuarioDto>();
		listaUsuario.get().forEach(usuario -> listaUsuarioDto.add(this.converterUsuarioParaDto(usuario)));
		response.setData(listaUsuarioDto);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/listar-por-id/{id}")
	public ResponseEntity<Response<UsuarioDto>> listarUsuarioPorId(
			@PathVariable("id") Long id){
		Response response = new Response();
		
		Optional<Usuario> usuario = this.usuarioService.buscarPorId(id);
		if(!usuario.isPresent()) {
			response.getErrors().add("Usuário não encontrado!");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.converterUsuarioParaDto(usuario.get()));
		return ResponseEntity.ok(response);
	}
	
	public Usuario converterDtoParaUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		
		usuario.setNome(usuarioDto.getNome());
		usuario.setEmail(usuarioDto.getEmail());
		usuarioDto.getSenha().ifPresent(senha -> usuario.setSenha(PasswordUtils.gerarBCrypt(senha)));
		usuario.setTipoEnum(UsuarioEnum.ROLE_USUARIO);
		return usuario;
	}
	
	public UsuarioDto converterUsuarioParaDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		
		usuarioDto.setId(usuario.getId());
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setEmail(usuario.getEmail());
		
		return usuarioDto;
	}
}
