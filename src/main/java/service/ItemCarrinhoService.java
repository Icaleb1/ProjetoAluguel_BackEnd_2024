package service;

import model.entity.Brinquedo;
import model.entity.ItemCarrinho;
import model.repository.ItemCarrinhoRepository;

public class ItemCarrinhoService {
	public ItemCarrinhoRepository carrinhoRepository = new ItemCarrinhoRepository();
	
	public ItemCarrinho adicionarItem(ItemCarrinho itemCarrinho) {
		return carrinhoRepository.adicionarItemCarrinho(itemCarrinho);
	}
	
	

}
