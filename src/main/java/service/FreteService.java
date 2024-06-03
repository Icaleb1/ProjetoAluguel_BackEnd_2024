package service;

import java.util.List;

import model.entity.Frete;
import model.repository.FreteRepository;

public class FreteService {
private FreteRepository freteRepository = new FreteRepository();
	
	public Frete salvar(Frete novoFrete) {
		return freteRepository.salvar(novoFrete);
	}
	
	public boolean exluir(int id) {
		return freteRepository.excluir(id);
	}
	
	public boolean alterar(Frete freteEditado) {
		return freteRepository.alterar(freteEditado);
	}
	
	public Frete consultarPorId(int id) {
		return freteRepository.consultarPorId(id);
	}
	
	public List<Frete> consultarTodos(){
		return freteRepository.consultarTodos();
	}


}
