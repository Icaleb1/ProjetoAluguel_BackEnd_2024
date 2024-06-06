package service;

import java.util.List;

import exception.AlugueisException;
import model.entity.Brinquedo;
import model.entity.Usuario;
import model.repository.BrinquedoRepository;

public class BrinquedoService {
private BrinquedoRepository brinquedoRepository = new BrinquedoRepository();
	
	public Brinquedo salvar(Brinquedo novoBrinquedo) throws AlugueisException {
		validarCamposObrigatorios(novoBrinquedo);
		return brinquedoRepository.salvar(novoBrinquedo);
	}
	
	public boolean exluir(int id) {
		return brinquedoRepository.excluir(id);
	}
	
	public boolean alterar(Brinquedo brinquedoEditado) throws AlugueisException {
		validarCamposObrigatorios(brinquedoEditado);
		return brinquedoRepository.alterar(brinquedoEditado);
	}
	
	public Brinquedo consultarPorId(int id) {
		return brinquedoRepository.consultarPorId(id);
	}
	
	public List<Brinquedo> consultarTodos(){
		return brinquedoRepository.consultarTodos();
	}
	
	private void validarCamposObrigatorios(Brinquedo brinquedoValidado) throws AlugueisException {
		String mensagemValidacao = "";
		
		if(brinquedoValidado.getNome() == null || brinquedoValidado.getNome().isEmpty()) {
			mensagemValidacao = "Nome obrigatório!";
		}
		if(brinquedoValidado.getDescricao() == null || brinquedoValidado.getDescricao().isEmpty()) {
			mensagemValidacao = "Descrição obrigatória!";
		}
		if (brinquedoValidado.getQuantEstoque() == 0) {
			mensagemValidacao = "Estoque é obrigatório!";		
		}
		if (brinquedoValidado.getValorDiaria() == 0) {
			mensagemValidacao = "Valor da diária é obrigatória!";
		}
		
		if (!mensagemValidacao.isEmpty()) {
			throw new AlugueisException("Preencha os seguintes campos: " + mensagemValidacao);
			
		}

	}


}
