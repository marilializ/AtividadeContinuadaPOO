package br.edu.cs.poo.ac.bolsa.entidade;

import java.io.Serializable;

public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String pais;
	private String estado;
	private String cidade;
	
	public Endereco(String logradouro, String cep, String numero, String complemento, String pais, String estado, String cidade) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
	}
	
	//getters
	public String getLogradouro() {return logradouro;}
	public String getCEP() {return cep;}
	public String getNumero() {return numero;}
	public String getComplemento() {return complemento;}
	public String getPais() {return pais;}
	public String getEstado() {return estado;}
	public String getCidade() {return cidade;}
	
	//setters
	public void setLogradouro(String logradouro) {this.logradouro = logradouro;}
	public void setCEP(String cep) {this.cep = cep;}
	public void setNumero(String numero) {this.numero = numero;}
	public void setComplemento(String complemento) {this.complemento = complemento;}
	public void setPais(String pais) {this.pais = pais;}
	public void setEstado(String estado) {this.estado = estado;}
	public void setCidade(String cidade) {this.cidade = cidade;}
	
}
