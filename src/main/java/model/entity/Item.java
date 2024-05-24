package model.entity;

public class Item {
	
	private int id;
	private Brinquedo brinquedo;
	private boolean alugado;
	
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Item(int id, Brinquedo brinquedo, boolean alugado) {
		super();
		this.id = id;
		this.brinquedo = brinquedo;
		this.alugado = alugado;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Brinquedo getBrinquedo() {
		return brinquedo;
	}


	public void setBrinquedo(Brinquedo brinquedo) {
		this.brinquedo = brinquedo;
	}


	public boolean isAlugado() {
		return alugado;
	}


	public void setAlugado(boolean alugado) {
		this.alugado = alugado;
	}

	
	
	
	
}
