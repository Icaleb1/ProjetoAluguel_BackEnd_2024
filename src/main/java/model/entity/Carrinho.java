package model.entity;

import java.util.List;

public class Carrinho {
	
	private int id;
	private int idUsuario;
	private List<ItemCarrinho> itens;
	
	
	public Carrinho() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Carrinho(int id, int idUsuario, List<ItemCarrinho> itens) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.itens = itens;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public List<ItemCarrinho> getItens() {
		return itens;
	}


	public void setItens(List<ItemCarrinho> itens) {
		this.itens = itens;
	}
	
	

}
