package service;

import java.util.List;

import exception.AlugueisException;
import model.entity.Aluguel;
import model.entity.Frete;
import model.repository.FreteRepository;

public class FreteService {
	
private static final double VALOR_GASOLINA_ATUAL = 5.58;
private FreteRepository freteRepository = new FreteRepository();
	
	public Frete salvar(Frete novoFrete) throws AlugueisException {
		validarCamposObrigatorios(novoFrete);
		double valorFreteCalculado = calcularValorFrete(novoFrete.getDistancia());
		novoFrete.setValor(valorFreteCalculado);
		return freteRepository.salvar(novoFrete);
	}
	
	public boolean exluir(int id) {
		return freteRepository.excluir(id);
	}
	
	public boolean alterar(Frete freteEditado) throws AlugueisException {
		validarCamposObrigatorios(freteEditado);
		return freteRepository.alterar(freteEditado);
	}
	
	public Frete consultarPorId(int id) {
		return freteRepository.consultarPorId(id);
	}
	
	public List<Frete> consultarTodos(){
		return freteRepository.consultarTodos();
	}
	
	private void validarCamposObrigatorios(Frete freteValidado) throws AlugueisException {
		String mensagemValidacao = "";
		
		if(freteValidado.getValor() == 0) {
			mensagemValidacao = "Valor obrigatório!";
		}
	
		if (!mensagemValidacao.isEmpty()) {
			throw new AlugueisException("Preencha os seguintes campos: " + mensagemValidacao);
			
		}

	}

  private double calcularValorFrete(double distancia) {
        return distancia * VALOR_GASOLINA_ATUAL;
    }
}
