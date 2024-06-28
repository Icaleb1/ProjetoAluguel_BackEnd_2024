package controller;

import java.util.List;

import exception.AlugueisException;
import filter.AuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import model.entity.Carrinho;
import model.entity.Endereco;
import model.entity.Usuario;
import service.CarrinhoService;
import service.UsuarioService;

@Path("/restrito/carrinho")
public class CarrinhoController {
	
	@Context
	private HttpServletRequest request;
	
	CarrinhoService carrinhoService = new CarrinhoService();
	UsuarioService usuarioService = new UsuarioService();
	
	
	@Path("/por-usuario/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Carrinho consultarCarrinhoDoUsuario(@PathParam("id") int idUsuario) throws AlugueisException{
		String idSessaoNoHeader = request.getHeader(AuthFilter.CHAVE_ID_SESSAO);
		if(idSessaoNoHeader == null || idSessaoNoHeader.isEmpty()) {
			throw new AlugueisException("Usuário sem permissão (idSessao não informado)");
		}
		
		Usuario usuarioAutenticado = this.usuarioService.consultarPorIdSessao(idSessaoNoHeader);
		
		if(usuarioAutenticado == null) {
			throw new AlugueisException("Usuário não encontrado");
		}
		
		if(!usuarioAutenticado.isAdministrador()
				&& usuarioAutenticado.getId() != idUsuario) {
			throw new AlugueisException("Usuário sem permissão de acesso");
		}
		
		return this.carrinhoService.consultarPorIdUsuario(idUsuario);
	}
	
	@GET
	@Path("/{idUsuario}")
	public Carrinho consultarPorIdUsuario(@PathParam("idUsuario") int idUsuario){
		 return carrinhoService.consultarPorIdUsuario(idUsuario);
	}
	
}
