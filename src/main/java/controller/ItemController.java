package controller;

import java.util.List;

import exception.AlugueisException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Brinquedo;
import model.entity.Item;
import service.ItemService;

@Path("/item")
public class ItemController {
	ItemService itemService = new ItemService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Item salvar(Item novoItem) throws AlugueisException {
		return itemService.salvar(novoItem);
	}
	

	@DELETE 
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return itemService.exluir(id);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Item itemAlterado) throws AlugueisException{
		return itemService.alterar(itemAlterado);
	}
	
	@GET
	@Path("/{id}")
	public Item consultarPorId(@PathParam("id") int id){
		 return itemService.consultarPorId(id);
	}
	
	@GET
	@Path("/todos")
	public List<Item> consultarTodas(){
		return itemService.consultarTodos();
	}


}
