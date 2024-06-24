package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.Brinquedo;
import model.entity.Carrinho;
import model.entity.ItemCarrinho;

public class CarrinhoRepository {
	
	public Carrinho criarCarrinho(int idUsuario) {
        Carrinho carrinho = new Carrinho();
        String query = "INSERT INTO db_camax.CARRINHO (ID_USUARIO) VALUES (?)";
        Connection conn = Banco.getConnection();
		PreparedStatement psmt = Banco.getPreparedStatementWithPk(conn, query);

        try {
            conn = Banco.getConnection();
            psmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, idUsuario);
            psmt.execute();

            // Obter o ID gerado para o carrinho
            int idCarrinho = -1;
            try (ResultSet generatedKeys = psmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idCarrinho = generatedKeys.getInt(1);
                }
            }

            if (idCarrinho != -1) {
                carrinho.setId(idCarrinho); // Definir o ID do carrinho criado
            }

        } catch (SQLException e) {
            System.out.println("Erro ao criar carrinho para o usuário: " + e.getMessage());
        } finally {
            Banco.closePreparedStatement(psmt);
            Banco.closeConnection(conn);
        }

        return carrinho;
    }


	
	 public Carrinho consultarPorIdUsuario(int idUsuario) {
	        Connection conn = Banco.getConnection();
	        PreparedStatement psmt = null;
	        ResultSet resultado = null;
	        Carrinho carrinho = new Carrinho();

	        String query = "SELECT c.ID, c.ID_USUARIO, ic.ID AS ITEM_ID, ic.ID_BRINQUEDO, ic.QUANTIDADE, " +
	                       "b.ID AS BRINQUEDO_ID, b.NOME, b.DESCRICAO, b.ESTOQUE_DISPONIVEL, b.ESTOQUE_TOTAL, b.VALOR_DIARIA " +
	                       "FROM db_camax.CARRINHO c " +
	                       "LEFT JOIN db_camax.ITEM_CARRINHO ic ON c.ID = ic.ID_CARRINHO " +
	                       "LEFT JOIN db_camax.BRINQUEDO b ON ic.ID_BRINQUEDO = b.ID " +
	                       "WHERE c.ID_USUARIO = ?";

	        try {
	            psmt = conn.prepareStatement(query);
	            psmt.setInt(1, idUsuario);
	            resultado = psmt.executeQuery();

	            List<ItemCarrinho> itensCarrinho = new ArrayList<>();
	            while (resultado.next()) {
	                if (carrinho.getId() == 0) {
	                    carrinho.setId(resultado.getInt("ID"));
	                    carrinho.setIdUsuario(resultado.getInt("ID_USUARIO"));
	                }

	                int itemId = resultado.getInt("ITEM_ID");
	                if (itemId > 0) {
	                    Brinquedo brinquedo = new Brinquedo();
	                    brinquedo.setId(resultado.getInt("BRINQUEDO_ID"));
	                    brinquedo.setNome(resultado.getString("NOME"));
	                    brinquedo.setDescricao(resultado.getString("DESCRICAO"));
	                    brinquedo.setEstoqueDisponivel(resultado.getInt("ESTOQUE_DISPONIVEL"));
	                    brinquedo.setEstoqueTotal(resultado.getInt("ESTOQUE_TOTAL"));
	                    brinquedo.setValorDiaria(resultado.getDouble("VALOR_DIARIA"));

	                    ItemCarrinho itemCarrinho = new ItemCarrinho();
	                    itemCarrinho.setId(itemId);
	                    itemCarrinho.setIdCarrinho(carrinho.getId());
	                    itemCarrinho.setBrinquedo(brinquedo);
	                    itemCarrinho.setQuantidade(resultado.getInt("QUANTIDADE"));

	                    itensCarrinho.add(itemCarrinho);
	                }
	            }
	            carrinho.setItens(itensCarrinho);

	        } catch (SQLException erro) {
	            System.out.println("Erro ao consultar carrinho para o usuário com o id: " + idUsuario);
	            System.out.println("Erro: " + erro.getMessage());
	        } finally {
	            Banco.closeResultSet(resultado);
	            Banco.closePreparedStatement(psmt);
	            Banco.closeConnection(conn);
	        }

	        return carrinho;
	    }
	}
