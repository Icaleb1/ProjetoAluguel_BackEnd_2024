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
import model.entity.Endereco;
import model.entity.Usuario;
import service.UsuarioService;

@Path ("/usuario")
public class UsuarioController {
	UsuarioService usuarioService = new UsuarioService();
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario salvar(Usuario novoUsuario) {
		return usuarioService.salvar(novoUsuario);
	}
	

	@DELETE 
	@Path("/{id}")
	public boolean excluir(@PathParam("id") int id) {
		return usuarioService.exluir(id);
	}
	
	@PUT
	@Path("/atualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean alterar(Usuario usuarioAlterado){
		return usuarioService.alterar(usuarioAlterado);
	}
	
	@GET
	@Path("/{id}")
	public Usuario consultarPorId(@PathParam("id") int id){
		 return usuarioService.consultarPorId(id);
	}
	
	@GET
	@Path("/todos")
	public List<Usuario> consultarTodas(){
		return usuarioService.consultarTodos();
	}

	


}
