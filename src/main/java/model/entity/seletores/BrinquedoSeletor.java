package model.entity.seletores;

public class BrinquedoSeletor extends BaseSeletor{
	
	private String nomeBrinquedo;
	private Double valorMinimo;
	private Double valorMaximo;
	
	public BrinquedoSeletor(String nomeBrinquedo, Double valorMinimo, Double valorMáximo) {
		super();
		this.nomeBrinquedo = nomeBrinquedo;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMáximo;
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

	public Double getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(Double valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public Double getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(Double valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	
	

}
