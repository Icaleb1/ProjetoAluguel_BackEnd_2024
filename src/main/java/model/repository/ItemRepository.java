package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Brinquedo;
import model.entity.Item;

public class ItemRepository {
	
	public Item salvar(Item novoItem) {
		String query = "INSERT INTO db_camax.item (id_aluguel, id_brinquedo, alugado) VALUES (?,?,?)";
		Connection conn = Banco.getConnection();
		PreparedStatement psmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			psmt.setInt(1, novoItem.getId_Aluguel());
			psmt.setInt(2, novoItem.getBrinquedo().getId());
			psmt.setBoolean(3, novoItem.isDisponivel());			
			psmt.execute();
			ResultSet resultado = psmt.getGeneratedKeys();
			if (resultado.next()) {
				novoItem.setId(resultado.getInt(1));	
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao salvar a novo item");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(psmt);
			Banco.closeConnection(conn);
		}
		
		return novoItem;
	}
	
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM db_camax.item WHERE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir item");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}
	
	public boolean alterar(Item itemEditado) {
		boolean alterou = false;
		String query = " UPDATE db_camax.item "
				     + " SET id_aluguel=?, id_brinquedo=?, alugado=?"
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setInt(1, itemEditado.getId_Aluguel());
			stmt.setInt(2, itemEditado.getBrinquedo().getId());
			stmt.setBoolean(3, itemEditado.isDisponivel());
			
			stmt.setInt(4, itemEditado.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar item");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}

	public boolean desalugar(int idItemDesalugar) {
		  boolean desalugar = false;
		  String query = "UPDATE db_camax.item SET alugado=false WHERE id=?";
		  Connection conn = Banco.getConnection();
		  PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		  try {
		    stmt.setInt(1, idItemDesalugar);
		    desalugar = stmt.executeUpdate() > 0;
		  } catch (SQLException erro) {
		    System.out.println("Erro ao desalugar item!");
		    System.out.println("Erro: " + erro.getMessage());
		  } finally {
		    Banco.closeStatement(stmt);
		    Banco.closeConnection(conn);
		  }
		  return desalugar;
	}
	
	public boolean alugar(int idItemAlugado) {
		  boolean alugado = false;
		  String query = "UPDATE db_camax.item SET alugado=true WHERE id=?";
		  Connection conn = Banco.getConnection();
		  PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		  try {
		    stmt.setInt(1, idItemAlugado);
		    alugado = stmt.executeUpdate() > 0;
		  } catch (SQLException erro) {
		    System.out.println("Erro ao alugar item!");
		    System.out.println("Erro: " + erro.getMessage());
		  } finally {
		    Banco.closeStatement(stmt);
		    Banco.closeConnection(conn);
		  }
		  return alugado;
	}
	
	public Item consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		Item item = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.item WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			BrinquedoRepository brinquedoRepository = new BrinquedoRepository();
			
			if(resultado.next()){
				item = new Item();
				item.setId(resultado.getInt("ID"));
				item.setId_Aluguel(resultado.getInt("ID_ALUGUEL"));
				item.setBrinquedo(brinquedoRepository.consultarPorId(resultado.getInt("ID_VACINA")));
				
				
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar item com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return item;
	}

	public ArrayList<Item> consultarTodos() {
		ArrayList<Item> items = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.item";
		
		try{
			resultado = stmt.executeQuery(query);
			BrinquedoRepository brinquedoRepository = new BrinquedoRepository();
			
			while(resultado.next()){
				Item item = new Item();
				item.setId(resultado.getInt("ID"));
				item.setId_Aluguel(resultado.getInt("ID_ALUGUEL"));
				item.setBrinquedo(brinquedoRepository.consultarPorId(resultado.getInt("ID_BRINQUEDO")));
				items.add(item);
			}

		} catch (SQLException erro){
			System.out.println("Erro ao consultar todos os items");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return items;
	}
	
	public ArrayList<Item> consultarTodosPorIdAluguel(int idAluguel) {
		ArrayList<Item> items = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " SELECT * FROM db_camax.item where id_aluguel = " + idAluguel;
		
		try{
			resultado = stmt.executeQuery(query);
			BrinquedoRepository brinquedoRepository = new BrinquedoRepository();
			
			while(resultado.next()){
				Item item = new Item();
				item.setId(resultado.getInt("ID"));
				item.setId_Aluguel(resultado.getInt("ID_ALUGUEL"));
				item.setBrinquedo(brinquedoRepository.consultarPorId(resultado.getInt("ID_VACINA")));
				items.add(item);
			}

		} catch (SQLException erro){
			System.out.println("Erro ao consultar todos os items");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return items;
	}

    public int contarItensPorBrinquedo(int idBrinquedo) {
        int quantidade = 0;
        String query = "SELECT COUNT(*) AS quantidade FROM db_camax.item WHERE id_brinquedo = ? AND alugado = false";
        Connection conn = Banco.getConnection();
        PreparedStatement psmt = Banco.getPreparedStatement(conn, query);

        try {
            psmt.setInt(1, idBrinquedo);
            ResultSet resultado = psmt.executeQuery();

            if (resultado.next()) {
                quantidade = resultado.getInt("quantidade");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao contar itens por brinquedo");
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closePreparedStatement(psmt);
            Banco.closeConnection(conn);
        }

        return quantidade;
    }



}
