package service;

import java.util.List;

import exception.AlugueisException;
import model.entity.Item;
import model.entity.Usuario;
import model.repository.ItemRepository;

public class ItemService {
private ItemRepository itemRepository = new ItemRepository();
	
	public Item salvar(Item novoItem) throws AlugueisException {
		validarCamposObrigatorios(novoItem);
		return itemRepository.salvar(novoItem);
	}
	
	public boolean exluir(int id) {
		return itemRepository.excluir(id);
	}
	
	public boolean alterar(Item itemEditado) throws AlugueisException {
		validarCamposObrigatorios(itemEditado);
		return itemRepository.alterar(itemEditado);
	}
	
	public Item consultarPorId(int id) {
		return itemRepository.consultarPorId(id);
	}
	
	public List<Item> consultarTodos(){
		return itemRepository.consultarTodos();
	}
	
	public List<Item> consultarTodosPorIdAluguel(int idAluguel){
		return itemRepository.consultarTodosPorIdAluguel(idAluguel);
	}
	
	private void validarCamposObrigatorios(Item itemValidado) throws AlugueisException {
		String mensagemValidacao = "";
		
		if (itemValidado.getBrinquedo() == null) {
			mensagemValidacao = "Brinquedo obrigatório!";
		}
		
		
		if (!mensagemValidacao.isEmpty()) {
			throw new AlugueisException("Preencha os seguintes campos: " + mensagemValidacao);
			
		}

	}


}
