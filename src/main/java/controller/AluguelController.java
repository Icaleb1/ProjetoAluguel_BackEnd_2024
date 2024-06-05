package controller;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Aluguel;
import model.entity.Brinquedo;
import service.AluguelService;

@Path("/aluguel")
public class AluguelController {
	AluguelService aluguelService = new AluguelService();
	
	
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Aluguel salvar(Aluguel novoAluguel) {
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
	public boolean alterar(Aluguel aluguelAlterado){
		return aluguelService.alterar(aluguelAlterado);
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
