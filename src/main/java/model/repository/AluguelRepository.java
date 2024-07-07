 package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
		    String queryAluguel = "UPDATE db_camax.aluguel "
		            + "SET id_usuario=?, id_endereco=?, data_aluguel=?, data_devolucao=?, "
		            + "data_devolucao_definitiva=?, valores_adicionais=?, valor_total=? "
		            + "WHERE id=?";
		    Connection conn = Banco.getConnection();
		    PreparedStatement psmtAluguel = null;
		    PreparedStatement psmtItem = null;

		    try {
		        conn.setAutoCommit(false); // Iniciar transação

		        // Atualizar informações do aluguel
		        psmtAluguel = Banco.getPreparedStatementWithPk(conn, queryAluguel);
		        psmtAluguel.setInt(1, aluguelEditado.getUsuario().getId());
		        psmtAluguel.setInt(2, aluguelEditado.getIdEnderecoDeEntrega());
		        psmtAluguel.setDate(3, Date.valueOf(LocalDate.now()));
		        psmtAluguel.setDate(4, aluguelEditado.getDataDevolucao());
		        psmtAluguel.setDate(5, aluguelEditado.getDataDevDefinitiva());
		        psmtAluguel.setDouble(6, aluguelEditado.getValoresAdicionais());
		        psmtAluguel.setDouble(7, aluguelEditado.getValorTotal());
		        psmtAluguel.setInt(8, aluguelEditado.getId());

		        alterou = psmtAluguel.executeUpdate() > 0;

		        if (alterou) {
		            
		            String queryItem = "UPDATE ITEM SET DISPONIVEL = false WHERE ID_ALUGUEL = ?";
		            psmtItem = conn.prepareStatement(queryItem);
		            psmtItem.setInt(1, aluguelEditado.getId());
		            psmtItem.executeUpdate();
		        }

		        conn.commit(); // Commit da transação
		    } catch (SQLException erro) {
		        try {
		            conn.rollback(); // Rollback em caso de erro
		        } catch (SQLException e) {
		            System.out.println("Erro ao realizar rollback: " + e.getMessage());
		        }
		        System.out.println("Erro ao atualizar aluguel: " + erro.getMessage());
		        alterou = false;
		    } finally {
		        Banco.closePreparedStatement(psmtAluguel);
		        Banco.closePreparedStatement(psmtItem);
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

	public boolean finalizarAluguel(Aluguel aluguelFinalizado) {
        boolean alterou = false;
        String queryAluguel = "UPDATE db_camax.aluguel "
                + "SET id_usuario=?, id_endereco=?, data_aluguel=?, data_devolucao=?, "
                + "data_devolucao_definitiva=?, valores_adicionais=?, valor_total=? "
                + "WHERE id=?";
        Connection conn = Banco.getConnection();
        PreparedStatement psmtAluguel = null;
        PreparedStatement psmtItem = null;

        try {
            conn.setAutoCommit(false); // Iniciar transação

            // Atualizar informações do aluguel
            psmtAluguel = Banco.getPreparedStatementWithPk(conn, queryAluguel);
            psmtAluguel.setInt(1, aluguelFinalizado.getUsuario().getId());
            psmtAluguel.setInt(2, aluguelFinalizado.getIdEnderecoDeEntrega());
            psmtAluguel.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now())); // Data de aluguel atual
            psmtAluguel.setDate(4, aluguelFinalizado.getDataDevolucao());
            psmtAluguel.setDate(5, aluguelFinalizado.getDataDevDefinitiva());
            psmtAluguel.setDouble(6, aluguelFinalizado.getValoresAdicionais());
            psmtAluguel.setDouble(7, aluguelFinalizado.getValorTotal());
            psmtAluguel.setInt(8, aluguelFinalizado.getId());

            alterou = psmtAluguel.executeUpdate() > 0;

            if (alterou) {
                // Atualizar os itens do aluguel para indisponíveis
                String queryItem = "UPDATE ITEM SET DISPONIVEL = false WHERE ID_ALUGUEL = ?";
                psmtItem = conn.prepareStatement(queryItem);
                psmtItem.setInt(1, aluguelFinalizado.getId());
                psmtItem.executeUpdate();
            }

            conn.commit(); // Commit da transação
        } catch (SQLException erro) {
            try {
                conn.rollback(); // Rollback em caso de erro
            } catch (SQLException e) {
                System.out.println("Erro ao realizar rollback: " + e.getMessage());
            }
            System.out.println("Erro ao finalizar aluguel: " + erro.getMessage());
            alterou = false;
        } finally {
            Banco.closePreparedStatement(psmtAluguel);
            Banco.closePreparedStatement(psmtItem);
            Banco.closeConnection(conn);
        }
        return alterou;
    }

		private List<Item> selecionarItensDisponiveis(int brinquedoId, int quantidade) {
    String query = "SELECT * FROM ITEM WHERE ID_BRINQUEDO = ? AND DISPONIVEL = true LIMIT ?";
    Connection conn = null;
    PreparedStatement psmt = null;
    ResultSet resultado = null;
    List<Item> itensDisponiveis = new ArrayList<>();

    try {
        conn = Banco.getConnection();
        psmt = conn.prepareStatement(query);
        psmt.setInt(1, brinquedoId);
        psmt.setInt(2, quantidade);
        resultado = psmt.executeQuery();

        while (resultado.next()) {
            Item item = new Item();
            item.setId(resultado.getInt("ID"));
            item.setBrinquedo(brinquedoRepository.consultarPorId(resultado.getInt("ID_BRINQUEDO")));
            item.setDisponivel(resultado.getBoolean("DISPONIVEL"));
            itensDisponiveis.add(item);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao selecionar itens disponíveis: " + e.getMessage());
    } finally {
        Banco.closeResultSet(resultado);
        Banco.closePreparedStatement(psmt);
        Banco.closeConnection(conn);
    }

    return itensDisponiveis;
}

		public boolean removerItemDoAluguel(int aluguelId, int itemId) {
		    String query = "UPDATE db_camax.ITEM SET ID_ALUGUEL = NULL, DISPONIVEL = TRUE WHERE ID = ? AND ID_ALUGUEL = ?";
		    Connection conn = null;
		    PreparedStatement psmt = null;
		    boolean sucesso = false;

		    try {
		        conn = Banco.getConnection();
		        psmt = conn.prepareStatement(query);
		        psmt.setInt(1, itemId);
		        psmt.setInt(2, aluguelId);

		        int rowsAffected = psmt.executeUpdate();
		        if (rowsAffected > 0) {
		            sucesso = true;
		        }

		    } catch (SQLException e) {
		        System.out.println("Erro ao remover item do aluguel: " + e.getMessage());
		    } finally {
		        Banco.closePreparedStatement(psmt);
		        Banco.closeConnection(conn);
		    }

		    return sucesso;
		}

		
		public boolean DevolucaoDosItens(int aluguelId) {
		    String updateItemQuery = "UPDATE db_camax.ITEM SET DISPONIVEL = true, ID_ALUGUEL = 0 WHERE ID_ALUGUEL = ?";
		    String updateAluguelQuery = "UPDATE db_camax.ALUGUEL SET DATA_DEVOLUCAO_DEFINITIVA = ? WHERE ID = ?";
		    Connection conn = null;
		    PreparedStatement psmtItem = null;
		    PreparedStatement psmtAluguel = null;
		    boolean sucesso = false;

		    try {
		        conn = Banco.getConnection();
		        conn.setAutoCommit(false); // Inicia a transação

		        // Atualiza os itens para disponíveis
		        psmtItem = conn.prepareStatement(updateItemQuery);
		        psmtItem.setInt(1, aluguelId);
		        int rowsAffectedItems = psmtItem.executeUpdate();

		        // Atualiza a data de devolução definitiva do aluguel
		        psmtAluguel = conn.prepareStatement(updateAluguelQuery);
		        psmtAluguel.setDate(1, Date.valueOf(java.time.LocalDate.now()));
		        psmtAluguel.setInt(2, aluguelId);
		        int rowsAffectedAluguel = psmtAluguel.executeUpdate();

		        if (rowsAffectedItems > 0 && rowsAffectedAluguel > 0) {
		            sucesso = true;
		        }

		        conn.commit(); // Commit da transação

		    } catch (SQLException e) {
		        try {
		            if (conn != null) {
		                conn.rollback(); // Rollback em caso de erro
		            }
		        } catch (SQLException ex) {
		            System.out.println("Erro ao realizar rollback: " + ex.getMessage());
		        }
		        System.out.println("Erro ao atualizar itens e data de devolução definitiva: " + e.getMessage());
		    } finally {
		        Banco.closePreparedStatement(psmtItem);
		        Banco.closePreparedStatement(psmtAluguel);
		        Banco.closeConnection(conn);
		    }

		    return sucesso;
		}

		public boolean verificarAluguelNaoDevolvido(int aluguelId) {
		    String selectAluguelQuery = "SELECT DATA_DEVOLUCAO_DEFINITIVA FROM db_camax.ALUGUEL WHERE ID = ?";
		    Connection conn = null;
		    PreparedStatement psmtAluguel = null;
		    ResultSet rsAluguel = null;

		    try {
		        conn = Banco.getConnection();
		        psmtAluguel = conn.prepareStatement(selectAluguelQuery);
		        psmtAluguel.setInt(1, aluguelId);
		        rsAluguel = psmtAluguel.executeQuery();

		        if (rsAluguel.next()) {
		            Date dataDevDefinitiva = rsAluguel.getDate("DATA_DEVOLUCAO_DEFINITIVA");
		            Date dataAtual = Date.valueOf(java.time.LocalDate.now());

		            if (dataDevDefinitiva != null && dataDevDefinitiva.before(dataAtual)) {
		                return false; // O aluguel já foi devolvido
		            }
		        }

		    } catch (SQLException e) {
		        System.out.println("Erro ao verificar se o aluguel já foi devolvido: " + e.getMessage());
		    } finally {
		        Banco.closeResultSet(rsAluguel);
		        Banco.closePreparedStatement(psmtAluguel);
		        Banco.closeConnection(conn);
		    }

		    return true; // Aluguel não foi devolvido ou não encontrado
		}

		
		public List<Aluguel> consultarAlugueisPorUsuario(int idUsuario) {
		    List<Aluguel> alugueis = new ArrayList<>();
		    String query = "SELECT * FROM db_camax.aluguel WHERE id_usuario = ? AND valor_total > 0";
		    Connection conn = null;
		    PreparedStatement psmt = null;
		    ResultSet resultado = null;

		    try {
		        conn = Banco.getConnection();
		        psmt = conn.prepareStatement(query);
		        psmt.setInt(1, idUsuario);
		        resultado = psmt.executeQuery();
		        
		        UsuarioRepository usuarioRepository = new UsuarioRepository();
		        ItemRepository itemRepository = new ItemRepository();

		        while (resultado.next()) {
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
		    } catch (SQLException e) {
		        System.out.println("Erro ao consultar aluguéis do usuário com valor total maior que zero: " + e.getMessage());
		    } finally {
		        Banco.closeResultSet(resultado);
		        Banco.closePreparedStatement(psmt);
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
		
	

