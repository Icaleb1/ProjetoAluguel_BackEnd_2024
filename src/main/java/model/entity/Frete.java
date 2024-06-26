package model.entity;

import java.util.List;

public class Frete {
	
	private int id;
	private double valor;
	private double distancia;
	
	
	public Frete() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Frete(int id, double valor, double distancia) {
		super();
		this.id = id;
		this.valor = valor;
		this.distancia = distancia;
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


	public double getDistancia() {
		return distancia;
	}


	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}



	
	

}
