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
import model.entity.Aluguel;
import model.entity.Frete;
import service.FreteService;

@Path("/restrito/frete")
public class FreteController {
	FreteService freteService = new FreteService();



	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Frete salvar(Frete novoFrete) throws AlugueisException {
		return freteService.salvar(novoFrete);
	}
	

	@DELETE 
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return freteService.exluir(id);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Frete freteAlterado) throws AlugueisException{
		return freteService.alterar(freteAlterado);
	}
	
	@GET
	@Path("/{id}")
	public Frete consultarPorId(@PathParam("id") int id){
		 return freteService.consultarPorId(id);
	}
	
	@GET
	@Path("/todos")
	public List<Frete> consultarTodas(){
		return freteService.consultarTodos();
	}

	
}
