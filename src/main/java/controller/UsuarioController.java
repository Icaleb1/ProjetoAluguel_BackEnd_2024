package controller;

import java.sql.SQLException;
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
import model.entity.Usuario;
import service.UsuarioService;

@Path ("/restrito/usuario")
public class UsuarioController {
	UsuarioService usuarioService = new UsuarioService();
	
	@Context private HttpServletRequest request;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario salvar(Usuario novoUsuario) throws AlugueisException {
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
	public boolean alterar(Usuario usuarioAlterado) throws AlugueisException{
		return usuarioService.alterar(usuarioAlterado);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean desativar(@PathParam("id") int idUsuarioDesativado) throws AlugueisException{
		return usuarioService.desativar(idUsuarioDesativado);
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
