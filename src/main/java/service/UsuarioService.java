package service;

import java.sql.SQLException;
import java.util.List;

import exception.AlugueisException;
import model.entity.Usuario;
import model.repository.CarrinhoRepository;
import model.repository.UsuarioRepository;

public class UsuarioService {
	
	private UsuarioRepository usuarioRepository = new UsuarioRepository();
	private CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
	
	public Usuario salvar(Usuario novoUsuario) throws AlugueisException {
		validarCpf(novoUsuario);
		validarCamposObrigatorios(novoUsuario);
		validarIdade(novoUsuario);
		
		Usuario usuarioSalvo = usuarioRepository.salvar(novoUsuario);

        carrinhoRepository.criarCarrinho(usuarioSalvo.getId());

        return usuarioSalvo;
	}
	
	public boolean exluir(int id) {
		return usuarioRepository.excluir(id);
	}
	
	public boolean alterar(Usuario usuarioEditado) throws AlugueisException {
		validarCpf(usuarioEditado);
		validarCamposObrigatorios(usuarioEditado);
		validarIdade(usuarioEditado);
		return usuarioRepository.alterar(usuarioEditado);
	}
	
		public boolean desativar(int idUsuarioDesativado) throws AlugueisException {
			return usuarioRepository.desativar(idUsuarioDesativado);
		}
		
		
	public Usuario consultarPorId(int id) {
		return usuarioRepository.consultarPorId(id);
	}
	
	public List<Usuario> consultarTodos(){
		return usuarioRepository.consultarTodos();
	}
	
	private void validarCamposObrigatorios(Usuario usuarioValidado) throws AlugueisException {
		String mensagemValidacao = "";
		
		if(usuarioValidado.getNome() == null || usuarioValidado.getNome().isEmpty()) {
			mensagemValidacao = "Nome obrigatório!";
		}
		if(usuarioValidado.getEmail() == null || usuarioValidado.getEmail().isEmpty()) {
			mensagemValidacao = "Email obrigatório!";
		}
		if (usuarioValidado.getCpf() == null || usuarioValidado.getCpf().isEmpty() || usuarioValidado.getCpf().length() != 11) {
			mensagemValidacao = "CPF obrigatório!";
		}
		if (usuarioValidado.getTelefone() == null || usuarioValidado.getTelefone().isEmpty()) {
			mensagemValidacao = "Telefone é obrigatório!";		
		}
		if (usuarioValidado.getData_nascimento() == null) {
			mensagemValidacao = "- informe a data de nascimento \n";
		}
		if (usuarioValidado.getSenha() == null || usuarioValidado.getSenha().isEmpty()) {
			mensagemValidacao = "Senha obrigatória!";
		}
		
		if (!mensagemValidacao.isEmpty()) {
			throw new AlugueisException("Preencha os seguintes campos: " + mensagemValidacao);
			
		}

	}

	private void validarCpf(Usuario novoUsuario) throws AlugueisException{
		if (usuarioRepository.cpfJaCadastrado(novoUsuario.getCpf())) {
			throw new AlugueisException("CPF" + novoUsuario.getCpf() + " já cadastrado");
		}
	}
	
	private void validarIdade(Usuario novoUsuario) throws AlugueisException{
		if(usuarioRepository.idadeInválida(novoUsuario.getData_nascimento())) {
			throw new AlugueisException("Data de nascimento" + novoUsuario.getData_nascimento() + " inválida!");
		}
	}

	
	
}
