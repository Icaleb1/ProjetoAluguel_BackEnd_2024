package service;

import java.util.List;

import model.entity.Item;
import model.repository.ItemRepository;

public class ItemService {
private ItemRepository itemRepository = new ItemRepository();
	
	public Item salvar(Item novoItem) {
		return itemRepository.salvar(novoItem);
	}
	
	public boolean exluir(int id) {
		return itemRepository.excluir(id);
	}
	
	public boolean alterar(Item itemEditado) {
		return itemRepository.alterar(itemEditado);
	}
	
	public Item consultarPorId(int id) {
		return itemRepository.consultarPorId(id);
	}
	
	public List<Item> consultarTodos(){
		return itemRepository.consultarTodos();
	}


}
