 package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.entity.Aluguel;

public class AluguelRepository {
		
		public Aluguel salvar(Aluguel novoAluguel) {
			String query = "INSERT INTO aluguel (id_usuario, id_frete, data_aluguel, data_devolucao,"
					+ " data_devolucao_definitiva, valor_total) VALUES (?,?,?,?,?,?)";
			
			Connection conn = Banco.getConnection();
			PreparedStatement psmt = Banco.getPreparedStatementWithPk(conn, query);
			
			try {
				psmt.setInt(1, novoAluguel.getUsuario().getId());
				psmt.setInt(2, novoAluguel.getFrete().getId());		
				psmt.setDate(3, novoAluguel.getDataAluguel());
				psmt.setDate(4, novoAluguel.getDataDevolucao());
				psmt.setDate(5, novoAluguel.getDataDevDefinitiva());
				psmt.setDouble(6, novoAluguel.getValorTotal());
				psmt.execute();
				ResultSet resultado = psmt.getGeneratedKeys();
				if (resultado.next()) {
					novoAluguel.setId(resultado.getInt(1));	
				}
			} catch (SQLException erro) {
				System.out.println("Erro ao salvar o novo aluguel");
				System.out.println("Erro: " + erro.getMessage());
			} finally {
				Banco.closePreparedStatement(psmt);
				Banco.closeConnection(conn);
			}
			
			return novoAluguel;
		}
		
		public boolean excluir(int id) {
			Connection conn = Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			boolean excluiu = false;
			String query = "DELETE FROM aluguel WHERE id = " + id;
			try {
				if (stmt.executeUpdate(query) == 1) {
					excluiu = true;
				}
			} catch (SQLException erro) {
				System.out.println("Erro ao excluir aluguel");
				System.out.println("Erro: " + erro.getMessage());
			} finally {
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}
			return excluiu;
		}
		
		public boolean alterar(Aluguel aluguelEditado) {
			boolean alterou = false;
			String query = " UPDATE db_camax.aluguel "
					     + " SET id_usuario=?, id_frete=?, data_aluguel=?, data_devolucao=?,"
					     + " data_devolucao_definitiva=?, valor_total"
					     + " WHERE id=? ";
			Connection conn = Banco.getConnection();
			PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
			try {
				stmt.setInt(1, aluguelEditado.getUsuario().getId());
				stmt.setInt(1, aluguelEditado.getFrete().getId());
				stmt.setDate(3, aluguelEditado.getDataAluguel());
				stmt.setDate(3, aluguelEditado.getDataDevolucao());
				stmt.setDate(3, aluguelEditado.getDataDevDefinitiva());
				
				
				stmt.setInt(7, aluguelEditado.getId());
				alterou = stmt.executeUpdate() > 0;
			} catch (SQLException erro) {
				System.out.println("Erro ao atualizar aluguel ");
				System.out.println("Erro: " + erro.getMessage());
			} finally {
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}
			return alterou;
		}

	}

