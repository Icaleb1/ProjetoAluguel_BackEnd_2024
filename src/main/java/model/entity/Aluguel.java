package model.entity;

import java.sql.Date;
import java.util.List;

public class Aluguel {
	
	private int id;
	private Usuario usuario;
	private List<Item> itens;
	private Date dataAluguel;
	private Date dataDevolucao;
	private Date dataDevDefinitiva;
	private double valoresAdicionais;
	private double valorTotal;
	private Endereco enderecoDeEntrega;
	
	
	public Aluguel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Aluguel(int id, Usuario usuario, List<Item> itens, Date dataAluguel, Date dataDevolucao, Date dataDevDefinitiva,
			double valorTotal, Frete frete, double valoresAdicionais, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.itens = itens;
		this.dataAluguel = dataAluguel;
		this.dataDevolucao = dataDevolucao;
		this.dataDevDefinitiva = dataDevDefinitiva;
		this.valoresAdicionais = valoresAdicionais;
		this.valorTotal = valorTotal;
		this.enderecoDeEntrega = enderecoDeEntrega;
			}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public List<Item> getItens() {
		return itens;
	}


	public void setItens(List<Item> itens) {
		this.itens = itens;
	}


	public Date getDataAluguel() {
		return dataAluguel;
	}


	public void setDataAluguel(Date dataAluguel) {
		this.dataAluguel = dataAluguel;
	}


	public Date getDataDevolucao() {
		return dataDevolucao;
	}


	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}


	public Date getDataDevDefinitiva() {
		return dataDevDefinitiva;
	}


	public void setDataDevDefinitiva(Date dataDevDefinitiva) {
		this.dataDevDefinitiva = dataDevDefinitiva;
	}


	public double getValorTotal() {
		return valorTotal;
	}


	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}


	

	public double getValoresAdicionais() {
		return valoresAdicionais;
	}


	public void setValoresAdicionais(double valoresAdicionais) {
		this.valoresAdicionais = valoresAdicionais;
	}


	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}


	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}


	


	
	
	
	

}
