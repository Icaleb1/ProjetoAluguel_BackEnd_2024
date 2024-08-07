package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Brinquedo;
import model.entity.seletores.BrinquedoSeletor;

public class BrinquedoRepository {
	
	public Brinquedo salvar(Brinquedo novoBrinquedo) {
		String query = "INSERT INTO db_camax.brinquedo (nome, descricao, estoque_disponivel, estoque_total, "
					 + "valor_diaria) VALUES (?,?,?,?,?)";
		Connection conn = Banco.getConnection();
		PreparedStatement psmt = Banco.getPreparedStatementWithPk(conn, query);
		
		try {
			psmt.setString(1, novoBrinquedo.getNome());
			psmt.setString(2, novoBrinquedo.getDescricao());
			psmt.setInt(3, novoBrinquedo.getEstoqueDisponivel());
			psmt.setInt(4, novoBrinquedo.getEstoqueTotal());
			psmt.setDouble(5, novoBrinquedo.getValorDiaria());			
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
		String query = "DELETE FROM db_camax.brinquedo WHERE id = " + id;
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
				     + " SET nome=?, descricao=?, estoque_disponivel=?, estoque_total=?, valor_diaria=?"
				     + " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, brinquedoEditado.getNome());
			stmt.setString(2, brinquedoEditado.getDescricao());
			stmt.setInt(3, brinquedoEditado.getEstoqueDisponivel());
			stmt.setInt(4, brinquedoEditado.getEstoqueTotal());
			stmt.setDouble(5, brinquedoEditado.getValorDiaria());
			
			stmt.setInt(6, brinquedoEditado.getId());
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
		String query = " SELECT * FROM db_camax.brinquedo WHERE id = " + id;
		
		try{
			resultado = stmt.executeQuery(query);
			
			if(resultado.next()){
				brinquedo = new Brinquedo();
				brinquedo.setId(resultado.getInt("ID"));
				brinquedo.setNome(resultado.getString("NOME"));
				brinquedo.setDescricao(resultado.getString("DESCRICAO"));
				brinquedo.setEstoqueTotal(verificarEstoqueTotal(resultado.getInt("ID")));
				brinquedo.setEstoqueDisponivel(verificarQuantidadeDisponivel(resultado.getInt("ID")));
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
		String query = " SELECT * FROM db_camax.brinquedo";
		
		try{
			resultado = stmt.executeQuery(query);
			
			while(resultado.next()){
				  Brinquedo brinquedo = new Brinquedo();
		            brinquedo.setId(resultado.getInt("ID"));
		            brinquedo.setNome(resultado.getString("NOME"));
		            brinquedo.setDescricao(resultado.getString("DESCRICAO"));
		            int idBrinquedo = resultado.getInt("ID");
		            int estoqueTotal = verificarEstoqueTotal(idBrinquedo);
		            int estoqueDisponivel = verificarQuantidadeDisponivel(idBrinquedo);
		            
		            atualizarEstoque(idBrinquedo, estoqueDisponivel, estoqueTotal);
		            
		            brinquedo.setEstoqueTotal(estoqueTotal);
		            brinquedo.setEstoqueDisponivel(estoqueDisponivel);
		            brinquedo.setValorDiaria(resultado.getDouble("VALOR_DIARIA"));
		            brinquedos.add(brinquedo);
			}

		} catch (SQLException erro){
			System.out.println("Erro ao consultar todos os brinquedos");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return brinquedos;
	}

	public boolean atualizarEstoque(int idBrinquedo, int novaQuantidadeDisponivel, int novoEstoqueTotal) {
	    boolean atualizou = false;
	    String query = "UPDATE db_camax.brinquedo SET estoque_disponivel = ?, estoque_total = ? WHERE id = ?";
	    Connection conn = Banco.getConnection();
	    PreparedStatement stmt = Banco.getPreparedStatement(conn, query);

	    try {
	        stmt.setInt(1, novaQuantidadeDisponivel);
	        stmt.setInt(2, novoEstoqueTotal);
	        stmt.setInt(3, idBrinquedo);

	        if (stmt.executeUpdate() > 0) {
	            atualizou = true;
	        }
	    } catch (SQLException erro) {
	        System.out.println("Erro ao atualizar estoque do brinquedo com ID: " + idBrinquedo);
	        System.out.println("Erro: " + erro.getMessage());
	    } finally {
	        Banco.closePreparedStatement(stmt);
	        Banco.closeConnection(conn);
	    }

	    return atualizou;
	}


    public int verificarQuantidadeDisponivel(int idBrinquedo) {
        int quantidadeDisponivel = 0;
        String query = "SELECT COUNT(*) AS qtd_disponivel FROM db_camax.ITEM WHERE ID_BRINQUEDO = ? "
        		+ "AND disponivel = true";
        
        Connection conn = Banco.getConnection();
        PreparedStatement stmt = Banco.getPreparedStatement(conn, query);
        ResultSet resultado = null;
        
        try {
            stmt.setInt(1, idBrinquedo);
            resultado = stmt.executeQuery();
            
            if (resultado.next()) {
            	quantidadeDisponivel = resultado.getInt("qtd_disponivel");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao verificar a quantidade disponivel do brinquedo com ID: " + idBrinquedo);
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }
        
        return quantidadeDisponivel;
    }

    public int verificarEstoqueTotal(int idBrinquedo) {
        int quantidade = 0;
        String query = "SELECT COUNT(*) AS qtd FROM db_camax.ITEM WHERE ID_BRINQUEDO = ? ";
        
        Connection conn = Banco.getConnection();
        PreparedStatement stmt = Banco.getPreparedStatement(conn, query);
        ResultSet resultado = null;
        
        try {
            stmt.setInt(1, idBrinquedo);
            resultado = stmt.executeQuery();
            
            if (resultado.next()) {
            	quantidade = resultado.getInt("qtd");
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao verificar a quantidade do brinquedo com ID: " + idBrinquedo);
            System.out.println("Erro: " + erro.getMessage());
        } finally {
            Banco.closeResultSet(resultado);
            Banco.closePreparedStatement(stmt);
            Banco.closeConnection(conn);
        }
        
        return quantidade;
    }
    
    public ArrayList<Brinquedo> consultarComFiltro(BrinquedoSeletor seletor){
		ArrayList<Brinquedo> brinquedos = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		
		ResultSet resultado = null;
		String query = " select * from db_camax.brinquedo";  
		boolean primeiro = true;
		if (seletor.getNomeBrinquedo() != null) {
			if (primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += "upper(brinquedo.nome) LIKE UPPER('%" + seletor.getNomeBrinquedo() + "%')";
			primeiro = false;
		}
		//TODO Implementar os casos de somente com um valor preenchido;
		if(seletor.getValorMinimo() != null && seletor.getValorMaximo() != null) {
			if(primeiro) {
				query += " WHERE ";
			}else {
				query += " AND ";
			}
			query += " valor_diaria between '" + seletor.getValorMinimo() + "' and '" + seletor.getValorMaximo() + "';";
			primeiro = false;
		}

		try{
			resultado = stmt.executeQuery(query);
			BrinquedoRepository brinquedoRepository = new BrinquedoRepository();
			while(resultado.next()){
				Brinquedo brinquedo = new Brinquedo();
				brinquedo.setId(Integer.parseInt(resultado.getString("ID")));
				brinquedo.setNome(resultado.getString("NOME"));
				brinquedo.setDescricao(resultado.getString("DESCRICAO"));
				brinquedo.setEstoqueTotal(verificarEstoqueTotal(resultado.getInt("ID")));
				brinquedo.setEstoqueDisponivel(verificarQuantidadeDisponivel(resultado.getInt("ID")));
				brinquedo.setValorDiaria(resultado.getDouble("VALOR_DIARIA"));
				brinquedos.add(brinquedo);
			}
		} catch (SQLException erro){
			System.out.println("Erro ao consultar todas as brinquedos");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return brinquedos;
		
	}



}





