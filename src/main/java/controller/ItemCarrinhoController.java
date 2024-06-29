package controller;

import java.util.List;

import exception.AlugueisException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import model.entity.Brinquedo;
import model.entity.ItemCarrinho;
import model.entity.seletores.BrinquedoSeletor;
import service.ItemCarrinhoService;

@Path("/restrito/itemCarrinho")
public class ItemCarrinhoController {
	ItemCarrinhoService itemCarrinhoService = new ItemCarrinhoService();
	
	@Context
	private HttpServletRequest request;

	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemCarrinho adicionarAoCarrinho(ItemCarrinho itemCarrinho)  {
		return itemCarrinhoService.adicionarItem(itemCarrinho);
	}
	
	@DELETE 
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int idItemCarrinho) {
		return itemCarrinhoService.removerItem(idItemCarrinho);
	}
	
	

}
