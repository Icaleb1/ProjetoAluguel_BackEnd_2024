package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Brinquedo;

public class BrinquedoRepository {
	
	public Brinquedo salvar(Brinquedo novoBrinquedo) {
		String query = "INSERT INTO brinquedo (nome, descricao, qtde_em_estoque, "
					 + "valor_diaria) VALUES (?,?,?,?)";
		Connection conn = Banco.getConnection();
		PreparedStatement psmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			psmt.setString(1, novoBrinquedo.getNome());
			psmt.setString(2, novoBrinquedo.getDescricao());
			psmt.setInt(3, novoBrinquedo.getQuantEstoque());
			psmt.setDouble(4, novoBrinquedo.getValorDiaria());			
			psmt.execute();
			ResultSet resultado = psmt.getGeneratedKeys();
		
			if (resultado.next()) {
				novoBrinquedo.setId(resultado.getInt(1));	
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar a novo Brinquedo");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(psmt);
			Banco.closeConnection(conn);
		}
		
		return novoBrinquedo;
	}
	
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM brinquedo WHERE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir brinquedo");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	public boolean alterar(Brinquedo brinquedoEditado) {
		boolean alterou = false;
		String query = " UPDATE db_camax.brinquedo "
				     + " SET nome=?, descricao=?, qtde_em_estoque=?, valor_diaria=?"
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, brinquedoEditado.getNome());
			stmt.setString(2, brinquedoEditado.getDescricao());
			stmt.setInt(3, brinquedoEditado.getQuantEstoque());
			stmt.setDouble(4, brinquedoEditado.getValorDiaria());
			
			stmt.setInt(7, brinquedoEditado.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar brinquedo");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}
	
	public Brinquedo consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Brinquedo brinquedo = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM brinquedo WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			
			if(resultado.next()){
				brinquedo = new Brinquedo();
				brinquedo.setId(resultado.getInt("ID"));
				brinquedo.setNome(resultado.getString("NOME"));
				brinquedo.setDescricao(resultado.getString("DESCRICAO"));
				brinquedo.setQuantEstoque(resultado.getInt("QTDE_EM_ESTOQUE"));
				brinquedo.setValorDiaria(resultado.getDouble("VALOR_DIARIA"));
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar brinquedo com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return brinquedo;
	}


	public ArrayList<Brinquedo> consultarTodos() {
		ArrayList<Brinquedo> brinquedos = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM brinquedo";
		
		try{
			resultado = stmt.executeQuery(query);
			
			while(resultado.next()){
				Brinquedo brinquedo = new Brinquedo();
				brinquedo.setId(resultado.getInt("ID"));
				brinquedo.setNome(resultado.getString("NOME"));
				brinquedo.setDescricao(resultado.getString("DESCRICAO"));
				brinquedo.setQuantEstoque(resultado.getInt("QTDE_EM_ESTOQUE"));
				brinquedo.setValorDiaria(resultado.getDouble("VALOR_DIARIA"));
				brinquedos.add(brinquedo);
			}

		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as pessoas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return brinquedos;
	}



}


