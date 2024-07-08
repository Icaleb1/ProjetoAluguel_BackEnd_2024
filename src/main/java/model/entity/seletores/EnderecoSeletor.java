package model.entity.seletores;

public class EnderecoSeletor extends BaseSeletor{

	private String nomeEndereco;
	
	public EnderecoSeletor(String nomeEndereco) {
		super();
		this.nomeEndereco = nomeEndereco;
	}

	public EnderecoSeletor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNomeEndereco() {
		return nomeEndereco;
	}

	public void setNomeEndereco(String nomeEndereco) {
		this.nomeEndereco = nomeEndereco;
	}
	
	
	
	

}

