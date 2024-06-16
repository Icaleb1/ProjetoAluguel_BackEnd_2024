package controller;

import java.util.List;

import exception.AlugueisException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Brinquedo;
import model.entity.ItemCarrinho;
import model.entity.seletores.BrinquedoSeletor;
import service.ItemCarrinhoService;

@Path("/itemCarrinho")
public class ItemCarrinhoController {
	ItemCarrinhoService itemCarrinhoService = new ItemCarrinhoService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemCarrinho adicionarAoCarrinho(ItemCarrinho itemCarrinho)  {
		return itemCarrinhoService.adicionarItem(itemCarrinho);
	}
	
	

}
