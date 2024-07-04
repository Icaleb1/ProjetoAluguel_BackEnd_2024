package service;

import java.util.List;

import model.entity.Carrinho;
import model.entity.ItemCarrinho;
import model.repository.CarrinhoRepository;

public class CarrinhoService {

	
	CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
	
	public Carrinho consultarPorIdUsuario(int idUsuario) {
		return carrinhoRepository.consultarPorIdUsuario(idUsuario);
	}
	
	public boolean adicionarItensAoAluguel(int aluguelId, List<ItemCarrinho> itensCarrinho) {
		return carrinhoRepository.adicionarItensAoAluguel(aluguelId, itensCarrinho);
	}
	
	public boolean limparCarrinho(int idCarrinho) {
		return carrinhoRepository.limparCarrinho(idCarrinho);
	}

}
