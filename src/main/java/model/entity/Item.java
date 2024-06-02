package model.entity;

public class Item {
	
	private int id;
	private int id_Aluguel;
	private Brinquedo brinquedo;
	private boolean alugado;
	
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Item(int id, int id_Aluguel, Brinquedo brinquedo, boolean alugado) {
		super();
		this.id = id;
		this.id_Aluguel = id_Aluguel;
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


	public int getAluguel() {
		return id_Aluguel;
	}


	public void setAluguel(int aluguel) {
		this.id_Aluguel = aluguel;
	}

	
	
	
	
}
