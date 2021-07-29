package com.ricardo.bluetech.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ricardo.bluetech.dto.ImovelDto;
import com.ricardo.bluetech.dto.VotoDto;
import com.ricardo.bluetech.entities.Imagem;
import com.ricardo.bluetech.entities.Imovel;
import com.ricardo.bluetech.entities.Usuario;
import com.ricardo.bluetech.entities.Voto;
import com.ricardo.bluetech.response.Response;
import com.ricardo.bluetech.services.ImagemService;
import com.ricardo.bluetech.services.ImovelService;
import com.ricardo.bluetech.services.UsuarioService;
import com.ricardo.bluetech.services.VotoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/imovel")
public class ImovelController {
	
	private static final Logger log = LoggerFactory.getLogger(ImovelController.class);
	
	@Autowired
	private ImovelService imovelService;
	
	@Autowired
	private ImagemService imagemService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private VotoService votoService;
	
	@PostMapping("/cadastrar-imovel/")
	public ResponseEntity<Response<ImovelDto>> cadastrarImovel(
			@Valid @RequestBody ImovelDto imovelDto,
			BindingResult result){
		Response<ImovelDto> response = new Response();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Imovel imovel = this.converterDtoParaImovel(imovelDto);
		imovel = this.imovelService.inserir(imovel);
		List<Imagem> listaImagem = this.converterDtoParaImagem(imovelDto);
		List<Imagem> listaAux = new ArrayList<>();
		for(int i=0; i<listaImagem.size(); i++) {
			listaImagem.get(i).setImovel(imovel);
			listaAux.add(this.imagemService.inserir(listaImagem.get(i)));
		}
		
		response.setData(this.converterDadosParaDto(imovel, listaAux, new Long(0)));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/listar-imoveis/")
	public ResponseEntity<Response<List<ImovelDto>>> todosImoveis(){
		Response<List<ImovelDto>> response = new Response();
		
		Optional<List<Imovel>> imoveis = this.imovelService.buscarTodos();
		if(!imoveis.isPresent()) {
			response.getErrors().add("Erro - não há imoveis cadastrados!");
			return ResponseEntity.badRequest().body(response);
		}
		
		List<ImovelDto> aux = new ArrayList<>();
		for(int i=0; i<imoveis.get().size(); i++) {
			Imovel imAux = new Imovel();
			imAux.setId(imoveis.get().get(i).getId());
			imAux.setNomeImovel(imoveis.get().get(i).getNomeImovel());
			Optional<List<Voto>> listVotos = this.votoService.buscarPorIdImovel(imAux.getId());
			aux.add(this.converterDadosParaDto(imAux, this.imagemService.buscarPorImovelId(imAux.getId()).get(), new Long(listVotos.get().size()) ));
		}
		
		response.setData(aux);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/listar-imovel-id/{id}")
	public ResponseEntity<Response<ImovelDto>> imovelPorId(@PathVariable("id") Long id){
		Response response = new Response();
		
		Optional<Imovel> imovel = this.imovelService.buscarPorId(id);
		if(!imovel.isPresent()) {
			response.getErrors().add("Erro - imovel não localizado!");
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<List<Imagem>> imagens = this.imagemService.buscarPorImovelId(imovel.get().getId());
		Optional<List<Voto>> listVotos = this.votoService.buscarPorIdImovel(id);
		response.setData(this.converterDadosParaDto(imovel.get(), imagens.get(), new Long(listVotos.get().size())));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/inserir-voto/")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	public ResponseEntity<Response<VotoDto>> inserirVoto(
			@Valid @RequestBody VotoDto votoDto, BindingResult result
			){
		Response response = new Response();

		
		Optional<Usuario> usuario = this.usuarioService.buscarPorId(votoDto.getIdUsuario());
		Optional<Imovel> imovel   = this.imovelService.buscarPorId(votoDto.getIdImovel());
		if(!usuario.isPresent()) {
			result.addError(new ObjectError("Voto", "Usuário não existente no banco de dados"));
		}

		if(!imovel.isPresent()) {
			result.addError(new ObjectError("Voto", "Imovel não existente no banco de dados"));
		}

		
		if(this.votoService.buscarPorIdUsuario( votoDto.getIdUsuario() ).isPresent() ) {
			result.addError(new ObjectError("Voto", "Você já votou, obrigado!"));
		}

		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Voto voto = new Voto();
		
		voto.setImovelId(imovel.get().getId());
		voto.setUsuarioId(usuario.get().getId());
		this.votoService.inserirVoto(voto);
		response.setData(votoDto);

		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/dados-votacao/")
	public ResponseEntity<Response<VotoDto>> relatorioVoto(){
		Response response = new Response();
		
		Optional<List<Voto>> votos = this.votoService.buscarTodos();
		
		log.info("Total de votos: "+votos.get().size());
		
		VotoDto votoDto = new VotoDto();
		votoDto.setTotalVotos( new Long(votos.get().size() ));
		response.setData(votoDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/dados-votacao-imovel/{id}")
	public ResponseEntity<Response<VotoDto>> relatorioVotoImovel(@PathVariable("id") Long id){
		Response response = new Response();
		
		Optional<Imovel> imovel = this.imovelService.buscarPorId(id);
		if(!imovel.isPresent()) {
			response.getErrors().add("Erro - imovel não encontrado!");
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<List<Voto>> voto = this.votoService.buscarPorIdImovel(id);
		List<String> usuarios = new ArrayList<>();
		for(int i=0; i<voto.get().size(); i++) {
			Usuario u = new Usuario();
			u = this.usuarioService.buscarPorId(voto.get().get(i).getUsuarioId()).get();
			usuarios.add(u.getNome());
		}
		
		log.info("imovel "+imovel.get().getNomeImovel()+" possui: "+voto.get().size()+" votos");
		
		VotoDto votoDto = new VotoDto();
		
		votoDto.setNomeImovel(imovel.get().getNomeImovel());
		votoDto.setTotalVotosImovel(new Long(voto.get().size()));
		votoDto.setNomeUsuario(usuarios);
		
		response.setData(votoDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/dados-geral-votos/")
	public ResponseEntity<Response<List<VotoDto>>> pegarTodosOsDados(){
		Response response = new Response();
		
		List<Imovel> imoveis = this.imovelService.buscarTodos().get();
		List<Voto> votosTotal = this.votoService.buscarTodos().get();
		List<VotoDto> votosDto = new ArrayList<>();
		
		
		imoveis.forEach(imovel -> {
			Optional<List<Voto>> voto = this.votoService.buscarPorIdImovel(imovel.getId());
			List<String> usuarios = new ArrayList<>();
			for(int i=0; i<voto.get().size(); i++) {
				Usuario u = new Usuario();
				u = this.usuarioService.buscarPorId(voto.get().get(i).getUsuarioId()).get();
				usuarios.add(u.getNome());
			}
			
			VotoDto votoDto = new VotoDto();
			
			votoDto.setTotalVotos(new Long(votosTotal.size()));
			votoDto.setNomeImovel(imovel.getNomeImovel());
			votoDto.setTotalVotosImovel(new Long(voto.get().size()));
			votoDto.setNomeUsuario(usuarios);
			votosDto.add(votoDto);
			
		});
		
		response.setData(votosDto);
		return ResponseEntity.ok(response);
	}
	
	public Imovel converterDtoParaImovel(ImovelDto imovelDto) {
		Imovel i = new Imovel();
		i.setNomeImovel(imovelDto.getNomeImovel());
		return i;
	}
	
	public List<Imagem> converterDtoParaImagem(ImovelDto imovelDto){
		List<Imagem> listaImagem = new ArrayList<>();
		
		imovelDto.getImagem().forEach(imagem -> listaImagem.add(imagem));
		
		return listaImagem;
	}
	
	public ImovelDto converterDadosParaDto(Imovel imovel, List<Imagem> listaImagem, Long totalVotos) {
		ImovelDto imovelDto = new ImovelDto();
		imovelDto.setId(imovel.getId());
		imovelDto.setNomeImovel(imovel.getNomeImovel());
		imovelDto.setTotalVotos(totalVotos);
		List<Imagem> listaAux = new ArrayList<>();
		for(int i=0; i<listaImagem.size(); i++) {
			Imagem aux = new Imagem();
			aux.setId(listaImagem.get(i).getId());
			aux.setImagemPath(listaImagem.get(i).getImagemPath());
			listaAux.add(aux);
		}
		
		imovelDto.setImagem(listaAux);
		return imovelDto;
	}
}
