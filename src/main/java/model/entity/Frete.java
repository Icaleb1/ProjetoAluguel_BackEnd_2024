package model.entity;

public class Frete {
	
	private int id;
	private double valor;
	
	
	public Frete() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Frete(int id, double valor) {
		super();
		this.id = id;
		this.valor = valor;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	

}
