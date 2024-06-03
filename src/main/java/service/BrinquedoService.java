package service;

import java.util.List;

import model.entity.Brinquedo;
import model.repository.BrinquedoRepository;

public class BrinquedoService {
private BrinquedoRepository brinquedoRepository = new BrinquedoRepository();
	
	public Brinquedo salvar(Brinquedo novoBrinquedo) {
		return brinquedoRepository.salvar(novoBrinquedo);
	}
	
	public boolean exluir(int id) {
		return brinquedoRepository.excluir(id);
	}
	
	public boolean alterar(Brinquedo brinquedoEditado) {
		return brinquedoRepository.alterar(brinquedoEditado);
	}
	
	public Brinquedo consultarPorId(int id) {
		return brinquedoRepository.consultarPorId(id);
	}
	
	public List<Brinquedo> consultarTodos(){
		return brinquedoRepository.consultarTodos();
	}


}
