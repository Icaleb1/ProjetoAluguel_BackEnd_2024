package model.repository;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.Endereco;
import model.entity.Usuario;

import model.repository.Banco;



public class UsuarioRepository {


	public Usuario salvar(Usuario novoUsuario) {
		String sql = " INSERT INTO usuario (nome, email, senha, cpf, "
				   + "		               telefone, adm, ativo) "
				   + " VALUES(?, ?, ?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoUsuario.getNome());
			stmt.setString(2, novoUsuario.getEmail());
			stmt.setString(3, novoUsuario.getSenha());
			stmt.setString(4, novoUsuario.getCpf());
			stmt.setInt(6, novoUsuario.getTelefone());
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
		String query = "DELETE FROM usuario WHERE id = " + id;
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
				     + " SET nome=?, email=?, senha=?, cpf=? "
				     + " telefone=?, adm=?, ativo=? "
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, usuarioEditado.getNome());
			stmt.setString(2, usuarioEditado.getEmail());
			stmt.setString(3, usuarioEditado.getSenha());
			stmt.setString(4, usuarioEditado.getCpf());
			stmt.setInt(5, usuarioEditado.getTelefone());
			stmt.setBoolean(6, usuarioEditado.isAdministrador());
			stmt.setBoolean(7, usuarioEditado.isAtivo());
			
			stmt.setInt(8, usuarioEditado.getId());
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


	public Usuario consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Usuario usuario = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM usuario WHERE id = " + id;
		
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
				usuario.setTelefone(resultado.getInt("TELEFONE")); 
				usuario.setAdministrador(resultado.getBoolean("ADMINISTRADOR"));
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
		String query = " SELECT * FROM pessoa";
		
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
		usuario.setTelefone(resultado.getInt("TELEFONE")); 
		usuario.setAdministrador(resultado.getBoolean("ADMINISTRADOR"));
		usuario.setAtivo(resultado.getBoolean("ATIVO"));
		usuario.setEnderecos(enderecoRepository.consultarTodosPorIdUsuario(resultado.getInt("ID")));
		
		
		return usuario;
	}

	public boolean cpfJaCadastrado(String cpf) {
		boolean cpfJaUtilizado = false;	
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String query = " SELECT count(id) FROM usuario WHERE cpf = " + cpf;
		
		try {
			ResultSet resultado = stmt.executeQuery(query);
			cpfJaUtilizado = (resultado.getInt(1) > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao consultar CPF. Causa: " + e.getMessage());
		}
		
		return cpfJaUtilizado;
	}

	
}
