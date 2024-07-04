	package model.entity;

import java.time.LocalDate;
import java.util.List;

public class Usuario {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private LocalDate data_nascimento;
	private String telefone;
	private boolean administrador;
	private List<Endereco> enderecos;    
	private boolean ativo;
	private String idSessao;
	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Usuario(int id, String nome, String email, String senha, String cpf, LocalDate data_nascimento, String telefone, boolean administrador,
			List<Endereco> enderecos, boolean ativo, String idSessao) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.data_nascimento = data_nascimento;
		this.telefone = telefone;
		this.administrador = administrador;
		this.enderecos = enderecos;
		this.ativo = ativo;
		this.idSessao = idSessao;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


	public boolean isAdministrador() {
		return administrador;
	}


	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}


	public List<Endereco> getEnderecos() {
		return enderecos;
	}


	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}


	public boolean isAtivo() {
		return ativo;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}


	public LocalDate getData_nascimento() {
		return data_nascimento;
	}


	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}


	public String getIdSessao() {
		return idSessao;
	}


	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	
	
	 
	
	

}
