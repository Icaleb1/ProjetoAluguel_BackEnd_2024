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

import model.entity.Usuario;
import model.entity.vacinacao.Pessoa;
import model.repository.Banco;
import model.repository.BaseRepository;

public class UsuarioRepository implements BaseRepository<Usuario> {

	@Override
	public Usuario salvar(Usuario novoUsuario) {
		String sql = " INSERT INTO pessoa (nome, cpf, sexo, id_pais, "
				   + "		               data_nascimento, tipo) "
				   + " VALUES(?, ?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoUsuario.getNome());
			stmt.setString(2, novoUsuario.getEmail());
			stmt.setString(3, novoUsuario.getSenha());
			stmt.setString(4, novoUsuario.getCpf());
			stmt.setInt(6, novoUsuario.getTelefone());
			stmt.setBoolean(7, novoUsuario.isAdministrador());
			
			Array arrayEnderecos = conexao.createArrayOf("VARCHAR", novoUsuario.getEnderecos().toArray());
			stmt.setArray(8, arrayEnderecos);
			
			stmt.setBoolean(9, novoUsuario.isAtivo());
			
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

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM pessoa WHERE id = " + id;
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

	@Override
	public boolean alterar(Usuario usuarioEditado) {
		boolean alterou = false;
		String query = " UPDATE exemplos.pessoa "
				     + " SET nome=?, cpf=?, sexo=?, id_pais=? "
				     + " data_nascimento=?, tipo=? "
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, usuarioEditado.getNome());
			stmt.setString(2, usuarioEditado.getEmail());
			stmt.setString(3, usuarioEditado.getSenha());
			stmt.setString(4, usuarioEditado.getCpf());
			stmt.setInt(6, usuarioEditado.getTelefone());
			stmt.setBoolean(7, usuarioEditado.isAdministrador());
			stmt.setBoolean(8, usuarioEditado.isAtivo());
			
			stmt.setInt(7, usuarioEditado.getId());
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

	@Override
	public Usuario consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Usuario usuario = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				usuario = new Usuario();
				usuario.setId(resultado.getInt("ID"));
				usuario.setNome(resultado.getString("NOME"));
				usuario.setEmail(resultado.getString("EMAIL"));
				usuario.setCpf(resultado.getString("CPF"));
				usuario.setTelefone(resultado.getInt("TELEFONE")); 
				usuario.setAdministrador(resultado.getBoolean("ADMINISTRADOR"));
				usuario.setAtivo(resultado.getBoolean("ATIVO"));
				
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

	@Override
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
		Usuario usuario = new Usuario();
		usuario.setId(resultado.getInt("ID"));
		usuario.setNome(resultado.getString("NOME"));
		usuario.setEmail(resultado.getString("EMAIL"));
		usuario.setCpf(resultado.getString("CPF"));
		usuario.setTelefone(resultado.getInt("TELEFONE")); 
		usuario.setAdministrador(resultado.getBoolean("ADMINISTRADOR"));
		usuario.setAtivo(resultado.getBoolean("ATIVO"));

		return usuario;
	}

	public boolean cpfJaCadastrado(String cpf) {
		boolean cpfJaUtilizado = false;	
		
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String query = " SELECT count(id) FROM pessoa WHERE cpf = " + cpf;
		
		try {
			ResultSet resultado = stmt.executeQuery(query);
			cpfJaUtilizado = (resultado.getInt(1) > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao consultar CPF. Causa: " + e.getMessage());
		}
		
		return cpfJaUtilizado;
	}

	public List<Usuario> consultarUsuario() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa WHERE tipo = " + ;
		
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
}
