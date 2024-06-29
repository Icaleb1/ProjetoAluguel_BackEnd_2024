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
import model.entity.Brinquedo;
import model.entity.seletores.BrinquedoSeletor;
import service.BrinquedoService;

@Path("/restrito/brinquedo")
public class BrinquedoController {
	BrinquedoService brinquedoService = new BrinquedoService();

	
	@Context
	private HttpServletRequest request;

	@POST
	@Path("/filtro")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Brinquedo> consultarComFiltros(BrinquedoSeletor seletor){
		 return brinquedoService.consultarComFiltros(seletor);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Brinquedo salvar(Brinquedo novoBrinquedo) throws AlugueisException {
		return brinquedoService.salvar(novoBrinquedo);
	}
	

	@DELETE 
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return brinquedoService.exluir(id);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Brinquedo brinquedoAlterado) throws AlugueisException{
		return brinquedoService.alterar(brinquedoAlterado);
	}
	
	@GET
	@Path("/{id}")
	public Brinquedo consultarPorId(@PathParam("id") int id){
		 return brinquedoService.consultarPorId(id);
	}
	
	@GET
	@Path("/todos")
	public List<Brinquedo> consultarTodas(){
		return brinquedoService.consultarTodos();
	}

	

}
