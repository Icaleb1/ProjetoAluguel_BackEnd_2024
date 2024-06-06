package model.entity;

public class Endereco {
	
	private int id;
	private String nome;
	private boolean principal;
	private int idUsuario;
	private int cep;
	private String estado;
	private String cidade;
	private String bairro;
	private String lote;
	private int numero;
	private String complemento;
	private String referencia;
	
	public Endereco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Endereco(int id, String nome, boolean principal, int idUsuario, int cep, String estado, String cidade, String bairro, String lote, int numero,
			String complemento, String referencia) {
		super();
		this.id = id;
		this.nome = nome;
		this.principal = principal;
		this.idUsuario = idUsuario;
		this.cep = cep;
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.lote = lote;
		this.numero = numero;
		this.complemento = complemento;
		this.referencia = referencia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	

}
