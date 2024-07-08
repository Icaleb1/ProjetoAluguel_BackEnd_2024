package service;

import java.util.ArrayList;
import java.util.List;

import exception.AlugueisException;
import model.entity.Endereco;
import model.entity.Usuario;
import model.entity.seletores.EnderecoSeletor;
import model.repository.EnderecoRepository;

public class EnderecoService {
	
	private EnderecoRepository enderecoRepository = new EnderecoRepository();
	
	public Endereco salvar(Endereco novoEndereco) throws AlugueisException {
		validarCamposObrigatorios(novoEndereco);
		return enderecoRepository.salvar(novoEndereco);
	}
	
	public boolean exluir(int id) {
		return enderecoRepository.excluir(id);
	}
	
	public boolean alterar(Endereco enderecoEditado) throws AlugueisException {
		validarCamposObrigatorios(enderecoEditado);
		return enderecoRepository.alterar(enderecoEditado);
	}
	
	public Endereco consultarPorId(int id) {
		return enderecoRepository.consultarPorId(id);
	}
	
	public List<Endereco> consultarTodos(){
		return enderecoRepository.consultarTodos();
	}
	
	public List<Endereco> consultarTodosPorIdUsuario(int id){
		return enderecoRepository.consultarTodosPorIdUsuario(id);
	}
	
	public Endereco consultarPrincipalPorIdUsuario(int id_usuario) {
		return enderecoRepository.consultarPrincipalPorIdUsuario(id_usuario);
	}
	
	private void validarCamposObrigatorios(Endereco enderecoValidado) throws AlugueisException {
		String mensagemValidacao = "";
		
		if (enderecoValidado.getIdUsuario() == 0) {
			mensagemValidacao = "Usuário obrigatório!";
		}
		if(enderecoValidado.getCep() == 0 ) {
			mensagemValidacao = "CEP obrigatório!";
		}
		if(enderecoValidado.getEstado() == null || enderecoValidado.getEstado().isEmpty()) {
			mensagemValidacao = "Estado obrigatório!";
		}
		if (enderecoValidado.getCidade() == null || enderecoValidado.getCidade().isEmpty()) {
			mensagemValidacao = "Cidade obrigatório!";
		}
		if (enderecoValidado.getBairro() == null || enderecoValidado.getBairro().isEmpty()) {
			mensagemValidacao = "Bairro obrigatório!";
		}
		if (enderecoValidado.getLote() == null || enderecoValidado.getLote().isEmpty()) {
			mensagemValidacao = "Lote obrigatório!";
		}
		if (enderecoValidado.getNumero() == 0) {
			mensagemValidacao = "Número obrigatório!";
		}
		
		if (!mensagemValidacao.isEmpty()) {
			throw new AlugueisException("Preencha os seguintes campos: " + mensagemValidacao);
			
		}

	}
	
	public ArrayList<Endereco> consultarComFiltro(EnderecoSeletor seletor, int idUsuario){
		return enderecoRepository.consultarComFiltro(seletor, idUsuario);
	}

}
