
package model.entity;

public class Item {
	
	private int id;
	private int id_Aluguel;
	private Brinquedo brinquedo;
	private boolean disponivel;
	
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Item(int id, int id_Aluguel, Brinquedo brinquedo, boolean disponivel) {
		super();
		this.id = id;
		this.id_Aluguel = id_Aluguel;
		this.brinquedo = brinquedo;
		this.disponivel = disponivel;
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




	public int getId_Aluguel() {
		return id_Aluguel;
	}


	public void setId_Aluguel(int id_Aluguel) {
		this.id_Aluguel = id_Aluguel;
	}


	public boolean isDisponivel() {
		return disponivel;
	}


	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}


	

	
	
	
	
}
