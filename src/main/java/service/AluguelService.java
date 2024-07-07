 package service;

import java.util.List;

import exception.AlugueisException;
import model.entity.Aluguel;
import model.entity.Frete;
import model.entity.Item;
import model.entity.ItemCarrinho;
import model.entity.Usuario;
import model.repository.AluguelRepository;
import model.repository.FreteRepository;
import model.repository.ItemRepository;

public class AluguelService {
private AluguelRepository aluguelRepository = new AluguelRepository();
private ItemRepository itemRepository = new ItemRepository();
private FreteRepository freteRepository = new FreteRepository();
	
	public Aluguel salvar(Aluguel novoAluguel) throws AlugueisException {
		//validarCamposObrigatorios(novoAluguel);
		//alugar(novoAluguel);
		return aluguelRepository.salvar(novoAluguel);
	}
	
	public boolean exluir(int id) {
		return aluguelRepository.excluir(id);
	}
	
	public boolean alterar(Aluguel aluguelEditado) throws AlugueisException {
		
		double valorTotalItens = calcularValorTotalItens(aluguelEditado);
	    double valorFrete = obterValorFrete(aluguelEditado.getId()); // Método para obter o valor do frete

	    double novoValorTotal = valorTotalItens + valorFrete;
	    aluguelEditado.setValorTotal(novoValorTotal);
	    
	    validarCamposObrigatorios(aluguelEditado);

		return aluguelRepository.alterar(aluguelEditado);
	}
	
	public boolean removerItemDoAluguel(int aluguelId, int itemId) {
		return aluguelRepository.removerItemDoAluguel(aluguelId, itemId);
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
		
		if (!mensagemValidacao.isEmpty()) {
			throw new AlugueisException("Preencha os seguintes campos: " + mensagemValidacao);
			
		}

	}

	private void alugar(Aluguel aluguel) {
	        List<Item> itens = itemRepository.consultarTodosPorIdAluguel(aluguel.getId());

	        for (Item item : itens) {
	            itemRepository.alugar(item.getId());
	        }
	    }
	
	
	private double calcularValorTotalItens(Aluguel aluguel) {
	    double valorTotal = 0.0;

	    for (Item item : aluguel.getItens()) {
	        valorTotal += item.getBrinquedo().getValorDiaria();
	    }

	    return valorTotal;
	}

	
	private double obterValorFrete(int idAluguel) {
	    double valorFrete = 0.0;

	    // Supondo que você tenha um método no FreteRepository para buscar o valor do frete por idAluguel
	    Frete frete = freteRepository.consultarPorIdAluguel(idAluguel);
	    if (frete != null) {
	        valorFrete = frete.getValor();
	    }

	    return valorFrete;
	}
		/*
		 * public boolean adicionarItensAoAluguel(int aluguelId, List<ItemCarrinho>
		 * itensCarrinho) { return aluguelRepository.adicionarItensAoAluguel(aluguelId,
		 * itensCarrinho); }
		 */
}
