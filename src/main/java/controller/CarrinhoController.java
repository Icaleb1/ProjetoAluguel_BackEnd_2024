package controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import model.entity.Carrinho;
import model.entity.Endereco;
import service.CarrinhoService;

@Path("/carrinho")
public class CarrinhoController {
	CarrinhoService carrinhoService = new CarrinhoService();
	
	@GET
	@Path("/{idUsuario}")
	public Carrinho consultarPorIdUsuario(@PathParam("idUsuario") int idUsuario){
		 return carrinhoService.consultarPorIdUsuario(idUsuario);
	}
	
}
