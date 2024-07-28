package model.entity;

public class ItemCarrinho {
	
	private int id;
	private int idCarrinho; 
	private Brinquedo brinquedo;
	private int quantidade;
	
	public ItemCarrinho() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemCarrinho(int id, int idCarrinho, Brinquedo brinquedo, int quantidade) {
		super();
		this.id = id;
		this.idCarrinho = idCarrinho;
		this.brinquedo = brinquedo;
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCarrinho() {
		return idCarrinho;
	}

	public void setIdCarrinho(int idCarrinho) {
		this.idCarrinho = idCarrinho;
	}

	public Brinquedo getBrinquedo() {
		return brinquedo;
	}

	public void setBrinquedo(Brinquedo brinquedo) {
		this.brinquedo = brinquedo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	

}
