package service;

import java.util.List;

import model.entity.Usuario;
import model.repository.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository usuarioRepository = new UsuarioRepository();
	
	public Usuario salvar(Usuario novoUsuario) {
		return usuarioRepository.salvar(novoUsuario);
	}
	
	public boolean exluir(int id) {
		return usuarioRepository.excluir(id);
	}
	
	public boolean alterar(Usuario usuarioEditado) {
		return usuarioRepository.alterar(usuarioEditado);
	}
	
	public Usuario consultarPorId(int id) {
		return usuarioRepository.consultarPorId(id);
	}
	
	public List<Usuario> consultarTodos(){
		return usuarioRepository.consultarTodos();
	}


}
