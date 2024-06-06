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
	private double valorTotal;
	private Frete frete;
	private int distancia;
	
	
	public Aluguel() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Aluguel(int id, Usuario usuario, List<Item> itens, Date dataAluguel, Date dataDevolucao, Date dataDevDefinitiva,
			double valorTotal, Frete frete, int distancia) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.itens = itens;
		this.dataAluguel = dataAluguel;
		this.dataDevolucao = dataDevolucao;
		this.dataDevDefinitiva = dataDevDefinitiva;
		this.valorTotal = valorTotal;
		this.frete = frete;
		this.distancia = distancia;
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


	public Frete getFrete() {
		return frete;
	}


	public void setFrete(Frete frete) {
		this.frete = frete;
	}


	public int getDistancia() {
		return distancia;
	}


	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}


	
	
	
	

}
