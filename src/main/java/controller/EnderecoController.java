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
import model.entity.Endereco;
import service.EnderecoService;

@Path ("/restrito/endereco")
public class EnderecoController {
	private EnderecoService enderecoService = new EnderecoService();
	
	@Context
	private HttpServletRequest request;

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Endereco salvar(Endereco novoEndereco) throws AlugueisException {
		return enderecoService.salvar(novoEndereco);
	}
	

	@DELETE 
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return enderecoService.exluir(id);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Endereco enderecoAlterado) throws AlugueisException{
		return enderecoService.alterar(enderecoAlterado);
	}
	
	@GET
	@Path("/{id}")
	public Endereco consultarPorId(@PathParam("id") int id){
		 return enderecoService.consultarPorId(id);
	}
	
	@GET
	@Path("/todos")
	public List<Endereco> consultarTodas(){
		return enderecoService.consultarTodos();
	}
	
	@GET
	@Path("/todos/{idUsuario}")
	public List<Endereco> consultarTodas(@PathParam("idUsuario") int idUsuario){
		return enderecoService.consultarTodosPorIdUsuario(idUsuario);
	}

	


}
