package controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Endereco;
import service.EnderecoService;

@Path ("/endereco")
public class EnderecoController {
	private EnderecoService enderecoService = new EnderecoService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Endereco salvar(Endereco novoEndereco) {
		return enderecoService.salvar(novoEndereco);
	}

}
