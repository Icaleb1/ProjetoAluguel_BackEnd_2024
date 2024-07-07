package controller;

import java.util.List;

import exception.AlugueisException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import model.entity.Aluguel;
import model.entity.Brinquedo;
import model.entity.ItemCarrinho;
import service.AluguelService;

@Path("/restrito/aluguel")
public class AluguelController {
	AluguelService aluguelService = new AluguelService();
	
	
	@Context
	private HttpServletRequest request;


	/*
	 * @PUT
	 * 
	 * @Path("/adicao-aluguel/{id_aluguel}")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public boolean
	 * adicionarItensAoAluguel(@PathParam("id_aluguel") int aluguelId,
	 * List<ItemCarrinho> itensCarrinho) throws AlugueisException { return
	 * aluguelService.adicionarItensAoAluguel(aluguelId, itensCarrinho); }
	 */
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Aluguel salvar(Aluguel novoAluguel) throws AlugueisException {
		return aluguelService.salvar(novoAluguel);
	}
	

	@DELETE 
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return aluguelService.exluir(id);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Aluguel aluguelAlterado) throws AlugueisException{
		return aluguelService.alterar(aluguelAlterado);
	}
	
	
	@PUT
	@Path("/remover-item/{aluguelId}/{itemId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean removerItemDoAluguel(@PathParam("aluguelId") int aluguelId, @PathParam("itemId") int itemId) {
		return aluguelService.removerItemDoAluguel(aluguelId, itemId);
	}
	
	@GET
	@Path("/{id}")
	public Aluguel consultarPorId(@PathParam("id") int id){
		 return aluguelService.consultarPorId(id);
	}
	
	@GET
	@Path("/todos")
	public List<Aluguel> consultarTodas(){
		return aluguelService.consultarTodos();
	}


}
