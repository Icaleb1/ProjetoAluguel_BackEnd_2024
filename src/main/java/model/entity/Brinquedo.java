package model.entity;

public class Brinquedo {
	
	private int id;
	private String nome;
	private String descricao;
	private int estoqueTotal;
	private int estoqueDisponivel;
	private double valorDiaria;
	
	
	public Brinquedo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Brinquedo(int id, String nome, String descricao, int estoqueTotal, int estoqueDisponivel, double valorDiaria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.estoqueTotal = estoqueTotal;
		this.estoqueDisponivel = estoqueDisponivel;
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


	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public int getEstoqueTotal() {
		return estoqueTotal;
	}

	public void setEstoqueTotal(int estoqueTotal) {
		this.estoqueTotal = estoqueTotal;
	}

	public int getEstoqueDisponivel() {
		return estoqueDisponivel;
	}

	public void setEstoqueDisponivel(int estoqueDisponivel) {
		this.estoqueDisponivel = estoqueDisponivel;
	}
	
	
	
	
	
	
	
	
	
}
