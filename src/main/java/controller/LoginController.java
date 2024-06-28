package controller;

import exception.AlugueisException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Usuario;
import model.entity.dto.UsuarioDto;
import service.LoginService;


@Path("/login")
public class LoginController {
	
private LoginService loginService = new LoginService();
	
	@POST
	@Path("/autenticar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario autenticar(UsuarioDto usuarioTentandoAutenticar) throws AlugueisException {
		return loginService.autenticar(usuarioTentandoAutenticar);
	}

}
