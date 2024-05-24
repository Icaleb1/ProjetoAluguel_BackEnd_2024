package model.entity;

public class Brinquedo {
	
	private int id;
	private String nome;
	private String descricao;
	private int quantEstoque;
	private double valorDiaria;
	
	
	public Brinquedo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Brinquedo(int id, String nome, String descricao, int quantEstoque, double valorDiaria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.quantEstoque = quantEstoque;
		this.valorDiaria = valorDiaria;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getQuantEstoque() {
		return quantEstoque;
	}


	public void setQuantEstoque(int quantEstoque) {
		this.quantEstoque = quantEstoque;
	}


	public double getValorDiaria() {
		return valorDiaria;
	}


	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}
	
	
	
	
	
	
	
	
	
}
