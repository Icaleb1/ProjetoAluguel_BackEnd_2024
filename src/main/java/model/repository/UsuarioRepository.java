package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import model.entity.Usuario;
import model.entity.dto.UsuarioDto;
import util.StringUtils;





public class UsuarioRepository {


	public Usuario salvar(Usuario novoUsuario) {
		String sql = " INSERT INTO db_camax.usuario (nome, email, senha, cpf, "
				   + "		               data_nascimento, telefone, adm, ativo) "
				   + " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoUsuario.getNome());
			stmt.setString(2, novoUsuario.getEmail());
			stmt.setString(3, util.StringUtils.cifrar(novoUsuario.getSenha()));
			stmt.setString(4, novoUsuario.getCpf());
			stmt.setDate(5, Date.valueOf(novoUsuario.getData_nascimento()));
			stmt.setString(6, novoUsuario.getTelefone());
			stmt.setBoolean(7, novoUsuario.isAdministrador());	
			stmt.setBoolean(8, novoUsuario.isAtivo());
			
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novoUsuario.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar novo usuário!");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoUsuario;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM db_camax.usuario WHERE id = " + id;
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir usuário!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}


	public boolean alterar(Usuario usuarioEditado) {
		boolean alterou = false;
		String query = " UPDATE db_camax.usuario "
				     + " SET nome=?, email=?, senha=?, cpf=?, "
				     + " data_nascimento=?, telefone=?, adm=?, ativo=? "
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, usuarioEditado.getNome());
			stmt.setString(2, usuarioEditado.getEmail());
			stmt.setString(3, util.StringUtils.cifrar(usuarioEditado.getSenha()));
			stmt.setString(4, usuarioEditado.getCpf());
			stmt.setDate(5, Date.valueOf(usuarioEditado.getData_nascimento()));
			stmt.setString(6, usuarioEditado.getTelefone());
			stmt.setBoolean(7, usuarioEditado.isAdministrador());
			stmt.setBoolean(8, usuarioEditado.isAtivo());
			
			stmt.setInt(9, usuarioEditado.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar usuário!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	public boolean desativar(int idUsuarioDesativado) {
		  boolean desativou = false;
		  String query = "UPDATE db_camax.usuario SET ativo=false WHERE id=?";
		  Connection conn = Banco.getConnection();
		  PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		  try {
		    stmt.setInt(1, idUsuarioDesativado);
		    desativou = stmt.executeUpdate() > 0;
		  } catch (SQLException erro) {
		    System.out.println("Erro ao desativar usuário!");
		    System.out.println("Erro: " + erro.getMessage());
		  } finally {
		    Banco.closeStatement(stmt);
		    Banco.closeConnection(conn);
		  }
		  return desativou;
	}

	public Usuario consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Usuario usuario = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.usuario WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			EnderecoRepository enderecoRepository = new EnderecoRepository();
			if(resultado.next()){
				usuario = new Usuario();
				usuario.setId(resultado.getInt("ID"));
				usuario.setNome(resultado.getString("NOME"));
				usuario.setEmail(resultado.getString("EMAIL"));
				usuario.setSenha(resultado.getString("SENHA"));
				usuario.setCpf(resultado.getString("CPF"));
				usuario.setData_nascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());
				usuario.setTelefone(resultado.getString("TELEFONE")); 
				usuario.setAdministrador(resultado.getBoolean("ADM"));
				usuario.setAtivo(resultado.getBoolean("ATIVO"));
				usuario.setEnderecos(enderecoRepository.consultarTodosPorIdUsuario(resultado.getInt("ID")));
			}
			
		} catch (SQLException erro){
			System.out.println("Erro ao consultar usuário com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}

	public ArrayList<Usuario> consultarTodos() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.usuario";
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Usuario usuario = construirDoResultSet(resultado);
				usuarios.add(usuario);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todos os usuários!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuarios;
	}

	private Usuario construirDoResultSet(ResultSet resultado) throws SQLException {
		EnderecoRepository enderecoRepository = new EnderecoRepository();
		Usuario usuario = new Usuario();
		usuario.setId(resultado.getInt("ID"));
		usuario.setNome(resultado.getString("NOME"));
		usuario.setEmail(resultado.getString("EMAIL"));
		usuario.setSenha(resultado.getString("SENHA"));
		usuario.setCpf(resultado.getString("CPF"));
		usuario.setData_nascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());
		usuario.setTelefone(resultado.getString("TELEFONE")); 
		usuario.setAdministrador(resultado.getBoolean("ADM"));
		usuario.setAtivo(resultado.getBoolean("ATIVO"));
		usuario.setEnderecos(enderecoRepository.consultarTodosPorIdUsuario(resultado.getInt("ID")));
		usuario.setIdSessao(resultado.getString("ID_SESSAO"));
		
		
		
		return usuario;
	}

	public boolean cpfJaCadastrado(String cpf) {
		boolean cpfJaUtilizado = false;	
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String query = " SELECT count(id) FROM db_camax.usuario WHERE cpf = " + cpf;
		
		try {
			ResultSet resultado = stmt.executeQuery(query);
			if(resultado.next()){
				cpfJaUtilizado = (resultado.getInt(1) > 0);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar CPF. Causa: " + e.getMessage());
		}
		
		return cpfJaUtilizado;
	}
	
	public boolean idadeInválida(LocalDate dataNascimento) {
        // Calcular a idade
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(dataNascimento, hoje);
        int idade = periodo.getYears();

        // Verificar se a idade é menor que 18
        return idade < 18;
    }
	
	public Usuario consultarPorLoginSenha(UsuarioDto usuarioDTO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		Usuario usuario = null;
		String query = " SELECT * FROM usuario "
				     + " WHERE email = '" + usuarioDTO.getLogin() + "'"
				     + " AND senha = '" + StringUtils.cifrar(usuarioDTO.getSenha()) + "'";
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				usuario = this.construirDoResultSet(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar usuario com login (" + usuarioDTO.getLogin() + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}

	public Usuario consultarPorLogin(String login) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		Usuario usuario = new Usuario();
		String query = " SELECT * FROM usuario "
				     + " WHERE email = '" + login + "'";
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				usuario = this.construirDoResultSet(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar jogador com login (" + login + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}

	public Usuario consultarPorIdSessao(String idSessao) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		Usuario usuario = new Usuario();
		String query = " SELECT * FROM usuario "
				     + " WHERE id_sessao = '" + idSessao + "'";
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				usuario = this.construirDoResultSet(resultado);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar usuario com idSessao (" + idSessao + ")");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}

	
	public boolean alterarIdSessao(Usuario novoUsuario) {
		boolean alterou = false;
		String query = " UPDATE usuario "
					 + " SET   id_sessao=? "
				     + " WHERE id=?";
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			pstmt.setString(1, novoUsuario.getIdSessao());
			pstmt.setInt(2, novoUsuario.getId());
			
			alterou = pstmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar idSessao do usuario");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}
}
