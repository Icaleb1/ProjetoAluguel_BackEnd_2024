package service;

import java.util.List;

import model.entity.Endereco;
import model.repository.EnderecoRepository;

public class EnderecoService {
	
	private EnderecoRepository enderecoRepository = new EnderecoRepository();
	
	public Endereco salvar(Endereco novoEndereco) {
		return enderecoRepository.salvar(novoEndereco);
	}
	
	public boolean exluir(int id) {
		return enderecoRepository.excluir(id);
	}
	
	public boolean alterar(Endereco enderecoEditado) {
		return enderecoRepository.alterar(enderecoEditado);
	}
	
	public Endereco consultarPorId(int id) {
		return enderecoRepository.consultarPorId(id);
	}
	
	public List<Endereco> consultarTodos(){
		return enderecoRepository.consultarTodos();
	}

}
