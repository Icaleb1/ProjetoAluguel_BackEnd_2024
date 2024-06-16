package model.entity.seletores;

public class BrinquedoSeletor extends BaseSeletor{
	
	private String nomeBrinquedo;
	private double valorMinimo;
	private double valorMáximo;
	
	public BrinquedoSeletor(String nomeBrinquedo, double valorMinimo, double valorMáximo) {
		super();
		this.nomeBrinquedo = nomeBrinquedo;
		this.valorMinimo = valorMinimo;
		this.valorMáximo = valorMáximo;
	}

	public BrinquedoSeletor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNomeBrinquedo() {
		return nomeBrinquedo;
	}

	public void setNomeBrinquedo(String nomeBrinquedo) {
		this.nomeBrinquedo = nomeBrinquedo;
	}

	public double getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public double getValorMáximo() {
		return valorMáximo;
	}

	public void setValorMáximo(double valorMáximo) {
		this.valorMáximo = valorMáximo;
	}
	
	

}
