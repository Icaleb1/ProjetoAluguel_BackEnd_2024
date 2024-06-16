package service;

import model.entity.Carrinho;
import model.repository.CarrinhoRepository;

public class CarrinhoService {
	
	CarrinhoRepository carrinhoRepository = new CarrinhoRepository();
	
	public Carrinho consultarPorIdUsuario(int idUsuario) {
		return carrinhoRepository.consultarPorIdUsuario(idUsuario);
	}

}
