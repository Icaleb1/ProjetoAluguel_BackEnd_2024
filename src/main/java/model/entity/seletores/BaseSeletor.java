package model.entity.seletores;

public abstract class BaseSeletor {
	
	private int limite;
	private int pagina;
	
	public BaseSeletor() {
		this.limite = 0;
		this.pagina = 0;
	}
	
	public boolean temPaginacao() {
		return this.limite > 0 && this.pagina > 0;
	}

	public int getOffset() {
		return this.limite * (this.pagina - 1);
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPagina() {
		return pagina;
	}

	public void setPagina(int pagina) {
		this.pagina = pagina;
	}
	
	 
	

}
