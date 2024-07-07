package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Frete;


public class FreteRepository{


	public Frete salvar(Frete novoFrete) {
		String sql = " INSERT INTO db_camax.frete (id_aluguel, valor, distancia) "
				   + " VALUES(?,?,?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);
		
		try {
			stmt.setInt(1, novoFrete.getId_aluguel());
			stmt.setDouble(2, novoFrete.getValor());
			stmt.setDouble(3, novoFrete.getDistancia());
		
			
			
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if(resultado.next()) {
				novoFrete.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar novo frete!");
			System.out.println("Erro: " + e.getMessage());
		}
		
		return novoFrete;
	}


	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM db_camax.frete WHERE id = " + id;
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir frete");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	
	public boolean alterar(Frete freteEditado) {
		boolean alterou = false;
		String query = " UPDATE db_camax.frete "
				     + " SET id_aluguel=?, valor=?, distancia=? "
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setInt(1, freteEditado.getId_aluguel());
			stmt.setDouble(2, freteEditado.getValor());
			stmt.setDouble(3, freteEditado.getDistancia());
			
			
			stmt.setInt(4, freteEditado.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar frete!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}


	public Frete consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Frete frete = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.frete WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				frete = new Frete();
				frete.setId(resultado.getInt("ID"));
				frete.setId_aluguel(resultado.getInt("ID_ALUGUEL"));
				frete.setValor(resultado.getDouble("VALOR"));
				frete.setDistancia(resultado.getDouble("DISTANCIA"));
				
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar frete com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return frete;
	}
	
	public Frete consultarPorIdAluguel(int idAluguel) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Frete frete = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.frete WHERE id_aluguel = " + idAluguel;
		
		try{
			resultado = stmt.executeQuery(query);
			if(resultado.next()){
				frete = new Frete();
				frete.setId(resultado.getInt("ID"));
				frete.setId_aluguel(resultado.getInt("ID_ALUGUEL"));
				frete.setValor(resultado.getDouble("VALOR"));
				frete.setDistancia(resultado.getDouble("DISTANCIA"));
				
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar aluguel com o id: " + idAluguel);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return frete;
	}


	public ArrayList<Frete> consultarTodos() {
		ArrayList<Frete> fretes = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.frete";
		
		try{
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				Frete frete = construirDoResultSet(resultado);
				fretes.add(frete);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas os fretes!");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return fretes;
	}

	private Frete construirDoResultSet(ResultSet resultado) throws SQLException {
		Frete frete = new Frete();
		frete.setId(resultado.getInt("ID"));
		frete.setId_aluguel(resultado.getInt("ID_ALUGUEL"));
		frete.setValor(resultado.getDouble("VALOR"));
		frete.setDistancia(resultado.getDouble("DISTANCIA"));
		

		return frete;
	}
}
