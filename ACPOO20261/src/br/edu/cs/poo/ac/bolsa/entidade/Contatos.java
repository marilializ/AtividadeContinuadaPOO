package br.edu.cs.poo.ac.bolsa.entidade;

import java.io.Serializable;

public class Contatos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String telefoneFixo;
	private String telefoneCelular;
	private String numeroWhatsApp;
	private String nomeParaContato;
	
	public Contatos() {}
	public Contatos (String email, String telefoneFixo, String telefoneCelular, String numeroWhatsApp, String nomeParaContato) {
		this.email = email;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCelular = telefoneCelular;
		this.numeroWhatsApp = numeroWhatsApp;
		this.nomeParaContato = nomeParaContato;
	}
	
	//getters
	public String getEmail() {return email;}
	public String getTelefoneFixo() {return telefoneFixo;}
	public String getTelefoneCelular() {return telefoneCelular;}
	public String getNumeroWhatsApp() {return numeroWhatsApp;}
	public String getNomeParaContato() {return nomeParaContato;}
	
	//setters
	public void setEmail(String email) {this.email = email;}
	public void setTelefoneFixo(String telefoneFixo) {this.telefoneFixo = telefoneFixo;}
	public void setTelefoneCelular(String telefoneCelular) {this.telefoneCelular = telefoneCelular;}
	public void setNumeroWhatsApp(String numeroWhatsApp) {this.numeroWhatsApp = numeroWhatsApp;}
	public void setNomeParaContato(String nomeParaContato) {this.nomeParaContato = nomeParaContato;}
}
