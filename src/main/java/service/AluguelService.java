package service;

import java.util.List;

import model.entity.Aluguel;
import model.repository.AluguelRepository;

public class AluguelService {
private AluguelRepository aluguelRepository = new AluguelRepository();
	
	public Aluguel salvar(Aluguel novoAluguel) {
		return aluguelRepository.salvar(novoAluguel);
	}
	
	public boolean exluir(int id) {
		return aluguelRepository.excluir(id);
	}
	
	public boolean alterar(Aluguel aluguelEditado) {
		return aluguelRepository.alterar(aluguelEditado);
	}
	
	public Aluguel consultarPorId(int id) {
		return aluguelRepository.consultarPorId(id);
	}
	
	public List<Aluguel> consultarTodos(){
		return aluguelRepository.consultarTodos();
	}


}
