 package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Aluguel;
import model.entity.Item;
import model.entity.Usuario;

public class AluguelRepository {
		
		public Aluguel salvar(Aluguel novoAluguel) {
			String query = "INSERT INTO db_camax.aluguel (id_usuario, id_frete, data_aluguel, data_devolucao,"
					+ " data_devolucao_definitiva, valor_total, distancia) VALUES (?,?,?,?,?,?,?)";
			
			Connection conn = Banco.getConnection();
			PreparedStatement psmt = Banco.getPreparedStatementWithPk(conn, query);
			
			try {
				psmt.setInt(1, novoAluguel.getUsuario().getId());
				psmt.setInt(2, novoAluguel.getFrete().getId());		
				psmt.setDate(3, novoAluguel.getDataAluguel());
				psmt.setDate(4, novoAluguel.getDataDevolucao());
				psmt.setDate(5, novoAluguel.getDataDevDefinitiva());
				psmt.setDouble(6, novoAluguel.getValorTotal());
				psmt.setInt(7, novoAluguel.getDistancia());
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
			String query = "DELETE FROM db_camax.aluguel WHERE id = " + id;
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
					     + " data_devolucao_definitiva=?, valor_total=?, distancia=?"
					     + " WHERE id=? ";
			Connection conn = Banco.getConnection();
			PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
			try {
				stmt.setInt(1, aluguelEditado.getUsuario().getId());
				stmt.setInt(2, aluguelEditado.getFrete().getId());
				stmt.setDate(3, aluguelEditado.getDataAluguel());
				stmt.setDate(4, aluguelEditado.getDataDevolucao());
				stmt.setDate(5, aluguelEditado.getDataDevDefinitiva());
				stmt.setDouble(6, aluguelEditado.getValorTotal());
				stmt.setInt(7, aluguelEditado.getDistancia());
				
				stmt.setInt(8, aluguelEditado.getId());
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

		public Aluguel consultarPorId(int id) {
			Connection conn = Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			
			Aluguel aluguel = null;
			ResultSet resultado = null;
			String query = " SELECT * FROM db_camax.aluguel WHERE id = " + id;
			
			try{
				resultado = stmt.executeQuery(query);

				FreteRepository freteRepository = new FreteRepository();
				UsuarioRepository usuarioRepository = new UsuarioRepository();
				ItemRepository itemRepository = new ItemRepository();
				
				if(resultado.next()){
					aluguel = new Aluguel();
					aluguel.setId(resultado.getInt("ID"));
					aluguel.setFrete(freteRepository.consultarPorId(resultado.getInt("ID_FRETE")));
					aluguel.setUsuario(usuarioRepository.consultarPorId(resultado.getInt("ID_USUARIO")));
					aluguel.setDataAluguel(resultado.getDate("DATA_ALUGUEL"));
					aluguel.setDataDevolucao(resultado.getDate("DATA_DEVOLUCAO"));
					aluguel.setDataDevDefinitiva(resultado.getDate("DATA_DEVOLUCAO_DEFINITIVA"));
					aluguel.setValorTotal(resultado.getDouble("VALOR_TOTAL"));
					aluguel.setItens(itemRepository.consultarTodosPorIdAluguel(resultado.getInt("ID")));
					aluguel.setDistancia(resultado.getInt("DISTANCIA"));
				}
				
			} catch (SQLException erro){
				System.out.println("Erro ao consultar aluguel com o id: " + id);
				System.out.println("Erro: " + erro.getMessage());
			} finally {
				Banco.closeResultSet(resultado);
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}
			return aluguel;
		}
		
		public ArrayList<Aluguel> consultarTodos() {
			ArrayList<Aluguel> alugueis = new ArrayList<>();
			Connection conn = Banco.getConnection();
			Statement stmt = Banco.getStatement(conn);
			
			ResultSet resultado = null;
			String query = " SELECT * FROM db_camax.aluguel";
			
			try{
				resultado = stmt.executeQuery(query);
				FreteRepository freteRepository = new FreteRepository();
				UsuarioRepository usuarioRepository = new UsuarioRepository();
				ItemRepository itemRepository = new ItemRepository();
				
				while(resultado.next()){
					Aluguel aluguel = new Aluguel();
					aluguel.setId(resultado.getInt("ID"));
					aluguel.setFrete(freteRepository.consultarPorId(resultado.getInt("ID_FRETE")));
					aluguel.setUsuario(usuarioRepository.consultarPorId(resultado.getInt("ID_USUARIO")));
					aluguel.setDataAluguel(resultado.getDate("DATA_ALUGUEL"));
					aluguel.setDataDevolucao(resultado.getDate("DATA_DEVOLUCAO"));
					aluguel.setDataDevDefinitiva(resultado.getDate("DATA_DEVOLUCAO_DEFINITIVA"));
					aluguel.setValorTotal(resultado.getDouble("VALOR_TOTAL"));
					aluguel.setItens(itemRepository.consultarTodosPorIdAluguel(resultado.getInt("ID")));
					aluguel.setDistancia(resultado.getInt("DISTANCIA"));
					alugueis.add(aluguel);
				}
			} catch (SQLException erro){
				System.out.println("Erro ao consultar todos os alugueis!");
				System.out.println("Erro: " + erro.getMessage());
			} finally {
				Banco.closeResultSet(resultado);
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}
			return alugueis;
		}

		
	}

