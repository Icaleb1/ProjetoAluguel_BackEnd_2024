package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Endereco;


public class EnderecoRepository implements BaseRepository<Endereco>{

	@Override
	public Endereco salvar(Endereco novoEndereco) {
		String sql = " INSERT INTO pessoa (nome, cpf, sexo, id_pais, "
				   + "		               data_nascimento, tipo) "
				   + " VALUES(?, ?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setString(1, novoEndereco.getBairro());
			stmt.setString(2, novoEndereco.getCidade());
			stmt.setString(3, novoEndereco.getComplemento());
			stmt.setString(4, novoEndereco.getEstado());
			stmt.setString(5, novoEndereco.getLote());
			stmt.setString(6, novoEndereco.getReferencia());
			stmt.setInt(7, novoEndereco.getCep());
			stmt.setInt(8, novoEndereco.getNumero());
			
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
			System.out.println("Erro ao excluir endereço");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Endereco EnderecoEditado) {
		boolean alterou = false;
		String query = " UPDATE exemplos.pessoa "
				     + " SET nome=?, cpf=?, sexo=?, id_pais=? "
				     + " data_nascimento=?, tipo=? "
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, EnderecoEditado.getBairro());
			stmt.setString(2, EnderecoEditado.getCidade());
			stmt.setString(3, EnderecoEditado.getComplemento());
			stmt.setString(4, EnderecoEditado.getEstado());
			stmt.setString(5, EnderecoEditado.getLote());
			stmt.setString(6, EnderecoEditado.getReferencia());
			stmt.setInt(7, EnderecoEditado.getCep());
			stmt.setInt(8, EnderecoEditado.getNumero());
			
			stmt.setInt(7, EnderecoEditado.getId());
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

	@Override
	public Endereco consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Endereco endereco = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				endereco = new Endereco();
				endereco.setId(resultado.getInt("ID"));
				endereco.setBairro(resultado.getString("NOME"));
				endereco.setCidade(resultado.getString("CPF"));
				endereco.setComplemento(resultado.getString("COMPLEMENTO"));
				endereco.setEstado(resultado.getString("ESTADO")); 
				endereco.setLote(resultado.getString("LOTE"));
				endereco.setReferencia(resultado.getString("REFERENCIA"));
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

	@Override
	public ArrayList<Endereco> consultarTodos() {
		ArrayList<Endereco> enderecos = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa";
		
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
		endereco.setBairro(resultado.getString("NOME"));
		endereco.setCidade(resultado.getString("CPF"));
		endereco.setComplemento(resultado.getString("COMPLEMENTO"));
		endereco.setEstado(resultado.getString("ESTADO")); 
		endereco.setLote(resultado.getString("LOTE"));
		endereco.setReferencia(resultado.getString("REFERENCIA"));
		endereco.setCep(resultado.getInt("CEP"));
		endereco.setNumero(resultado.getInt("NUMERO"));

		return endereco;
	}
}
