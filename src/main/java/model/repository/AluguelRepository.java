 package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.Aluguel;
import model.entity.Brinquedo;
import model.entity.Item;
import model.entity.ItemCarrinho;
import model.entity.Usuario;

public class AluguelRepository {
	
	BrinquedoRepository brinquedoRepository = new BrinquedoRepository();
		
		public Aluguel salvar(Aluguel novoAluguel) {
			String query = "INSERT INTO db_camax.aluguel (id_usuario, id_endereco, data_aluguel, data_devolucao,"
					+ " data_devolucao_definitiva, valores_adicionais, valor_total) VALUES (?,?,?,?,?,?,?)";
			
			Connection conn = Banco.getConnection();
			PreparedStatement psmt = Banco.getPreparedStatementWithPk(conn, query);
			
			try {
				psmt.setInt(1, novoAluguel.getUsuario().getId());
				psmt.setInt(2, novoAluguel.getIdEnderecoDeEntrega());
				psmt.setDate(3, novoAluguel.getDataAluguel());
				psmt.setDate(4, novoAluguel.getDataDevolucao());
				psmt.setDate(5, novoAluguel.getDataDevDefinitiva());
				psmt.setDouble(6, novoAluguel.getValoresAdicionais());
				psmt.setDouble(7, novoAluguel.getValorTotal());
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
					     + " SET id_usuario=?, id_endereco=?, data_aluguel=?, data_devolucao=?,"
					     + " data_devolucao_definitiva=?, valores_adicionais=?, valor_total=?"
					     + " WHERE id=? ";
			Connection conn = Banco.getConnection();
			PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
			try {
				stmt.setInt(1, aluguelEditado.getUsuario().getId());
				stmt.setInt(3, aluguelEditado.getIdEnderecoDeEntrega());
				stmt.setDate(4, aluguelEditado.getDataAluguel());
				stmt.setDate(5, aluguelEditado.getDataDevolucao());
				stmt.setDate(6, aluguelEditado.getDataDevDefinitiva());
				stmt.setDouble(7, aluguelEditado.getValoresAdicionais());
				stmt.setDouble(8, aluguelEditado.getValorTotal());
				
				stmt.setInt(9, aluguelEditado.getId());
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
				EnderecoRepository enderecoRepository = new EnderecoRepository();
				
				if(resultado.next()){
					aluguel = new Aluguel();
					aluguel.setId(resultado.getInt("ID"));
					aluguel.setUsuario(usuarioRepository.consultarPorId(resultado.getInt("ID_USUARIO")));
					aluguel.setIdEnderecoDeEntrega(resultado.getInt("ID_ENDERECO"));
					aluguel.setDataAluguel(resultado.getDate("DATA_ALUGUEL"));
					aluguel.setDataDevolucao(resultado.getDate("DATA_DEVOLUCAO"));
					aluguel.setDataDevDefinitiva(resultado.getDate("DATA_DEVOLUCAO_DEFINITIVA"));
					aluguel.setValoresAdicionais(resultado.getDouble("VALORES_ADICIONAIS"));
					aluguel.setValorTotal(resultado.getDouble("VALOR_TOTAL"));
					aluguel.setItens(itemRepository.consultarTodosPorIdAluguel(resultado.getInt("ID")));
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
				EnderecoRepository enderecoRepository = new EnderecoRepository();
				
				while(resultado.next()){
					Aluguel aluguel = new Aluguel();
					aluguel.setId(resultado.getInt("ID"));
					aluguel.setUsuario(usuarioRepository.consultarPorId(resultado.getInt("ID_USUARIO")));
					aluguel.setIdEnderecoDeEntrega(resultado.getInt("ID_ENDERECO"));
					aluguel.setDataAluguel(resultado.getDate("DATA_ALUGUEL"));
					aluguel.setDataDevolucao(resultado.getDate("DATA_DEVOLUCAO"));
					aluguel.setDataDevDefinitiva(resultado.getDate("DATA_DEVOLUCAO_DEFINITIVA"));
					aluguel.setValoresAdicionais(resultado.getDouble("VALORES_ADICIONAIS"));
					aluguel.setValorTotal(resultado.getDouble("VALOR_TOTAL"));
					aluguel.setItens(itemRepository.consultarTodosPorIdAluguel(resultado.getInt("ID")));
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
		
	/*
	 * public boolean adicionarItensAoAluguel(int aluguelId, List<ItemCarrinho>
	 * itensCarrinho) { String query =
	 * "UPDATE ITEM SET ID_ALUGUEL = ?, DISPONIVEL = false WHERE ID = ?"; Connection
	 * conn = null; PreparedStatement psmt = null; boolean sucesso = true;
	 * 
	 * try { conn = Banco.getConnection(); psmt = conn.prepareStatement(query);
	 * 
	 * for (ItemCarrinho itemCarrinho : itensCarrinho) { List<Item> itensDisponiveis
	 * = selecionarItensDisponiveis(itemCarrinho.getBrinquedo().getId(),
	 * itemCarrinho.getQuantidade());
	 * 
	 * for (Item item : itensDisponiveis) { psmt.setInt(1, aluguelId);
	 * psmt.setInt(2, item.getId()); psmt.addBatch(); // Adiciona a operação ao
	 * batch para execução em lote } }
	 * 
	 * psmt.executeBatch(); // Executa todas as atualizações em lote
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Erro ao adicionar itens ao aluguel: " + e.getMessage());
	 * sucesso = false; } finally { Banco.closePreparedStatement(psmt);
	 * Banco.closeConnection(conn); }
	 * 
	 * return sucesso; }
	 * 
	 * 
	 * // Método auxiliar para selecionar itens disponíveis public List<Item>
	 * selecionarItensDisponiveis(int brinquedoId, int quantidade) { String query =
	 * "SELECT * FROM ITEM WHERE ID_BRINQUEDO = ? AND DISPONIVEL = true LIMIT ?";
	 * Connection conn = null; PreparedStatement psmt = null; ResultSet resultado =
	 * null; List<Item> itensDisponiveis = new ArrayList<>();
	 * 
	 * try { conn = Banco.getConnection(); psmt = conn.prepareStatement(query);
	 * psmt.setInt(1, brinquedoId); psmt.setInt(2, quantidade); resultado =
	 * psmt.executeQuery();
	 * 
	 * while (resultado.next()) { Item item = new Item();
	 * item.setId(resultado.getInt("ID"));
	 * item.setBrinquedo(brinquedoRepository.consultarPorId(resultado.getInt(
	 * "ID_BRINQUEDO"))); item.setDisponivel(resultado.getBoolean("DISPONIVEL"));
	 * itensDisponiveis.add(item); }
	 * 
	 * } catch (SQLException e) {
	 * System.out.println("Erro ao selecionar itens disponíveis: " +
	 * e.getMessage()); } finally { Banco.closeResultSet(resultado);
	 * Banco.closePreparedStatement(psmt); Banco.closeConnection(conn); }
	 * 
	 * return itensDisponiveis; } }
	 */
		
	

