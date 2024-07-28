package service;

import exception.AlugueisException;
import model.entity.Brinquedo;
import model.entity.Carrinho;
import model.entity.ItemCarrinho;
import model.repository.CarrinhoRepository;
import model.repository.ItemCarrinhoRepository;

public class ItemCarrinhoService {
	public ItemCarrinhoRepository ItemCarrinhoRepository = new ItemCarrinhoRepository();
	public CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
	
	public ItemCarrinho adicionarItem(ItemCarrinho itemCarrinho) throws AlugueisException {
		verificarItensIguais(itemCarrinho, itemCarrinho.getIdCarrinho());
		
		return ItemCarrinhoRepository.adicionarItemCarrinho(itemCarrinho);
	}
	
	public boolean removerItem(int idItemCarrinho) {
		return ItemCarrinhoRepository.removerItemCarrinho(idItemCarrinho);
	}
	
	public boolean verificarItensIguais(ItemCarrinho itemCarrinho, int idCarrinho) throws AlugueisException{
		Carrinho carrinho = carrinhoRepository.consultarPorId(idCarrinho);
		
		for (ItemCarrinho item : carrinho.getItens()) {
			 System.out.println("Item no carrinho: " + item.getBrinquedo().getId());
		        System.out.println("Item a ser verificado: " + itemCarrinho.getBrinquedo().getId());

			if(item.getBrinquedo().getId() == itemCarrinho.getBrinquedo().getId() ) {
				throw new AlugueisException("O brinquedo já está inserido no carrinho!");
			}
		}
		return true;
	}
	
	

}
