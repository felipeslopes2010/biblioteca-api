package br.com.alura.biblioteca.service;

import java.util.Random;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alura.biblioteca.dto.AtualizacaoUsuarioFormDto;
import br.com.alura.biblioteca.dto.UsuarioDto;
import br.com.alura.biblioteca.dto.UsuarioFormDto;
import br.com.alura.biblioteca.modelo.Perfil;
import br.com.alura.biblioteca.modelo.Usuario;
import br.com.alura.biblioteca.repository.PerfilRepository;
import br.com.alura.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PerfilRepository perfilRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Page<UsuarioDto> listar(Pageable paginacao) {
		
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(t -> modelMapper.map(t, UsuarioDto.class));
		
	}

	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto) {

		Usuario usuario = modelMapper.map(dto, Usuario.class);
		usuario.setId(null);
		
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		usuario.adicionarPerfil(perfil);

		String senha = new Random().nextInt(999999) + "";
		usuario.setSenha(bCryptPasswordEncoder.encode(senha));

		usuarioRepository.save(usuario);
		
		return modelMapper.map(usuario, UsuarioDto.class);
		
	}
	
	@Transactional
	public UsuarioDto atualizar(AtualizacaoUsuarioFormDto dto) {
		Usuario usuario = usuarioRepository.getById(dto.getId());		
		usuario.atualizarInformacoes(dto.getLogin(),dto.getNome());		
		return modelMapper.map(usuario, UsuarioDto.class);
	}
	
	@Transactional
	public void remover(Long id) {
		try{
			usuarioRepository.deleteById(id);
			usuarioRepository.flush();
		}catch(org.springframework.dao.DataIntegrityViolationException e) {
			throw new RuntimeException("Existe registro vinculado a esse usuário, exclua o registro primeiro");
		}
		
		
	}
	
	public UsuarioDto detalhar(Long id) {
		Usuario usuario = usuarioRepository
				.findById(id)
				.orElseThrow(() -> new EntityNotFoundException());
				return modelMapper.map(usuario, UsuarioDto.class);
	}
	
}
