package service;

import java.util.List;

import exception.AlugueisException;
import model.entity.Aluguel;
import model.entity.Usuario;
import model.repository.AluguelRepository;

public class AluguelService {
private AluguelRepository aluguelRepository = new AluguelRepository();
	
	public Aluguel salvar(Aluguel novoAluguel) throws AlugueisException {
		validarCamposObrigatorios(novoAluguel);
		return aluguelRepository.salvar(novoAluguel);
	}
	
	public boolean exluir(int id) {
		return aluguelRepository.excluir(id);
	}
	
	public boolean alterar(Aluguel aluguelEditado) throws AlugueisException {
		validarCamposObrigatorios(aluguelEditado);
		return aluguelRepository.alterar(aluguelEditado);
	}
	
	public Aluguel consultarPorId(int id) {
		return aluguelRepository.consultarPorId(id);
	}
	
	public List<Aluguel> consultarTodos(){
		return aluguelRepository.consultarTodos();
	}
	
	private void validarCamposObrigatorios(Aluguel aluguelValidado) throws AlugueisException {
		String mensagemValidacao = "";
		
		if(aluguelValidado.getUsuario() == null) {
			mensagemValidacao = "Usuário obrigatório!";
		}
		if (aluguelValidado.getDataAluguel() == null) {
			mensagemValidacao = "Data de aluguel obrigatório!";
		}
		if (aluguelValidado.getDataDevolucao() == null) {
			mensagemValidacao = "Data de devolução obrigatória!";
		}
		if (aluguelValidado.getDataDevDefinitiva() == null) {
			mensagemValidacao = "Data de devolução definitiva obrigatória!";
		}
		if (aluguelValidado.getValorTotal() == 0) {
			mensagemValidacao = "Valor total é obrigatório!";
		}
		if (aluguelValidado.getFrete() == null) {
			mensagemValidacao = "Frete obrigatório!";
		}
		
		if (!mensagemValidacao.isEmpty()) {
			throw new AlugueisException("Preencha os seguintes campos: " + mensagemValidacao);
			
		}

	}


}
