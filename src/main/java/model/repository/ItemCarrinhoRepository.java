package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.Brinquedo;
import model.entity.ItemCarrinho;

public class ItemCarrinhoRepository {
	
	public ItemCarrinho adicionarItemCarrinho(ItemCarrinho itemCarrinho) {
        String query = "INSERT INTO db_camax.ITEM_CARRINHO (ID_CARRINHO, ID_BRINQUEDO, QUANTIDADE) VALUES (?, ?, ?)";
        Connection conn = Banco.getConnection();
		PreparedStatement psmt = Banco.getPreparedStatementWithPk(conn, query);
		
        try {
            psmt.setInt(1, itemCarrinho.getIdCarrinho());
            psmt.setInt(2, itemCarrinho.getBrinquedo().getId());
            psmt.setInt(3, itemCarrinho.getQuantidade());
            psmt.execute();
            ResultSet resultado = psmt.getGeneratedKeys();
			if (resultado.next()) {
				itemCarrinho.setId(resultado.getInt(1));	
			}

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar brinquedo ao carrinho: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(psmt);
            Banco.closeConnection(conn);
        }
		return itemCarrinho;
    }
	
	
	public boolean removerItemCarrinho(int idItemCarrinho) {
	    String query = "DELETE FROM db_camax.ITEM_CARRINHO WHERE ID = ?";
	    Connection conn = Banco.getConnection();
	    PreparedStatement psmt = Banco.getPreparedStatement(conn, query);

	    try {
	        psmt.setInt(1, idItemCarrinho);
	        
	        int rowsAffected = psmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        System.out.println("Erro ao remover brinquedo do carrinho: " + e.getMessage());
	        return false;
	    } finally {
	        Banco.closePreparedStatement(psmt);
	        Banco.closeConnection(conn);
	    }
	}



}
