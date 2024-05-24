package model.entity;

public class Frete {
	
	private int id;
	private String descricao;
	private double valor;
	
	
	public Frete() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Frete(int id, String descricao, double valor) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.valor = valor;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}
	
	
	

}
