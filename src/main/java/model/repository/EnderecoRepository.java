package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Brinquedo;
import model.entity.Endereco;
import model.entity.seletores.BrinquedoSeletor;
import model.entity.seletores.EnderecoSeletor;
import service.EnderecoService;


public class EnderecoRepository{

	public Endereco salvar(Endereco novoEndereco) {
		String sql = " INSERT INTO db_camax.endereco (nome, id_usuario, bairro, cidade, complemento, "
				   + "		               estado, lote, referencias, cep, numero) "
				   + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoEndereco.getNome());
			stmt.setInt(2, novoEndereco.getIdUsuario());
			stmt.setString(3, novoEndereco.getBairro());
			stmt.setString(4, novoEndereco.getCidade());
			stmt.setString(5, novoEndereco.getComplemento());
			stmt.setString(6, novoEndereco.getEstado());
			stmt.setString(7, novoEndereco.getLote());
			stmt.setString(8, novoEndereco.getReferencia());
			stmt.setInt(9, novoEndereco.getCep());
			stmt.setInt(10, novoEndereco.getNumero());
			
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novoEndereco.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar novo endereço!");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoEndereco;
	}

	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM db_camax.endereco WHERE id = " + id;
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir endereço");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	public boolean alterar(Endereco enderecoEditado) {
		boolean alterou = false;
		String query = " UPDATE db_camax.endereco "
				     + " SET nome=?, id_usuario=?, bairro=?, cidade=?, complemento=?, "
				     + " estado=?, lote=?, referencias=?, cep=?, numero=? "
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, enderecoEditado.getNome());
			stmt.setInt(2, enderecoEditado.getIdUsuario());
			stmt.setString(3, enderecoEditado.getBairro());
			stmt.setString(4, enderecoEditado.getCidade());
			stmt.setString(5, enderecoEditado.getComplemento());
			stmt.setString(6, enderecoEditado.getEstado());
			stmt.setString(7, enderecoEditado.getLote());
			stmt.setString(8, enderecoEditado.getReferencia());
			stmt.setInt(9, enderecoEditado.getCep());
			stmt.setInt(10, enderecoEditado.getNumero());
			
			stmt.setInt(11, enderecoEditado.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar endereço!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	public Endereco consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Endereco endereco = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.endereco WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				endereco = new Endereco();
				endereco.setId(resultado.getInt("ID"));
				endereco.setNome(resultado.getString("NOME"));
				endereco.setIdUsuario(resultado.getInt("ID_USUARIO"));
				endereco.setBairro(resultado.getString("BAIRRO"));
				endereco.setCidade(resultado.getString("CIDADE"));
				endereco.setComplemento(resultado.getString("COMPLEMENTO"));
				endereco.setEstado(resultado.getString("ESTADO")); 
				endereco.setLote(resultado.getString("LOTE"));
				endereco.setReferencia(resultado.getString("REFERENCIAs"));
				endereco.setCep(resultado.getInt("CEP"));
				endereco.setNumero(resultado.getInt("NUMERO"));
				
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar endereco com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return endereco;
	}

	public ArrayList<Endereco> consultarTodos() {
		ArrayList<Endereco> enderecos = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.endereco";
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Endereco endereco = construirDoResultSet(resultado);
				enderecos.add(endereco);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todos os endereços!	");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return enderecos;
	}

	private Endereco construirDoResultSet(ResultSet resultado) throws SQLException {
		Endereco endereco = new Endereco();
		endereco.setId(resultado.getInt("ID"));
		endereco.setNome(resultado.getString("NOME"));
		endereco.setIdUsuario(resultado.getInt("ID_USUARIO"));
		endereco.setBairro(resultado.getString("BAIRRO"));
		endereco.setCidade(resultado.getString("CIDADE"));
		endereco.setComplemento(resultado.getString("COMPLEMENTO"));
		endereco.setEstado(resultado.getString("ESTADO")); 
		endereco.setLote(resultado.getString("LOTE"));
		endereco.setReferencia(resultado.getString("REFERENCIAS"));
		endereco.setCep(resultado.getInt("CEP"));
		endereco.setNumero(resultado.getInt("NUMERO"));

		return endereco;
	}
	
	public ArrayList<Endereco> consultarTodosPorIdUsuario(int idUsuario) {
		ArrayList<Endereco> enderecos = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.endereco WHERE id_usuario = " + idUsuario;
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Endereco endereco = construirDoResultSet(resultado);
				enderecos.add(endereco);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todos os endereços do usuario com ID = " + idUsuario);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return enderecos;
	}

	public Endereco consultarPrincipalPorIdUsuario(int idUsuario) {
	    Connection conn = Banco.getConnection();
	    Statement stmt = Banco.getStatement(conn);
	    
	    Endereco endereco = null;
	    ResultSet resultado = null;
	    String query = "SELECT * FROM db_camax.endereco WHERE id_usuario = " + idUsuario + " AND principal = true";
	    
	    try {
	        resultado = stmt.executeQuery(query);
	        if(resultado.next()) {
	            endereco = new Endereco();
	            endereco.setId(resultado.getInt("ID"));
	            endereco.setNome(resultado.getString("NOME"));
	            endereco.setIdUsuario(resultado.getInt("ID_USUARIO"));
	            endereco.setBairro(resultado.getString("BAIRRO"));
	            endereco.setCidade(resultado.getString("CIDADE"));
	            endereco.setComplemento(resultado.getString("COMPLEMENTO"));
	            endereco.setEstado(resultado.getString("ESTADO")); 
	            endereco.setLote(resultado.getString("LOTE"));
	            endereco.setReferencia(resultado.getString("REFERENCIAS"));
	            endereco.setCep(resultado.getInt("CEP"));
	            endereco.setNumero(resultado.getInt("NUMERO"));
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao consultar endereço principal para o usuário com id: " + idUsuario);
	        System.out.println("Erro: " + erro.getMessage());
	    } finally {
	        Banco.closeResultSet(resultado);
	        Banco.closeStatement(stmt);
	        Banco.closeConnection(conn);
	    }
	    return endereco;
	}
	
	public ArrayList<Endereco> consultarComFiltro(EnderecoSeletor seletor, int idUsuario) {
	    ArrayList<Endereco> enderecos = new ArrayList<>();
	    Connection conn = Banco.getConnection();
	    Statement stmt = Banco.getStatement(conn);
	    
	    ResultSet resultado = null;
	    String query = "SELECT * FROM db_camax.endereco WHERE id_usuario = " + idUsuario;
	    
	    if (seletor.getNomeEndereco() != null) {
	        query += " AND upper(nome) LIKE UPPER('%" + seletor.getNomeEndereco() + "%')";
	    }
	    
	    try {
	        resultado = stmt.executeQuery(query);
	        while (resultado.next()) {
	            Endereco endereco = construirDoResultSet(resultado);
	            enderecos.add(endereco);
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao consultar todos os seus endereços!");
	        System.out.println("Erro: " + erro.getMessage());
	    } finally {
	        Banco.closeResultSet(resultado);
	        Banco.closeStatement(stmt);
	        Banco.closeConnection(conn);
	    }
	    return enderecos;
	}

	 

}
