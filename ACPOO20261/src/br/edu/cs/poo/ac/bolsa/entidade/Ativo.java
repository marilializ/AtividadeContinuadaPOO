package br.edu.cs.poo.ac.bolsa.entidade;

import java.io.Serializable;

public class Ativo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long codigo;
	private String descricao;
	private double valorMinimoAplicacao;
	private double valorMaximoAplicacao;
	private double taxaMensalMinima;
	private double taxaMensalMaxima;
	private FaixaRenda faixaMinimaPermitida;
	private int prazoEmMeses;
	
	public Ativo() {}
	public Ativo(long codigo, String descricao, double valorMinimoAplicacao, double valorMaximoAplicacao,
				double taxaMensalMinima, double taxaMensalMaxima, FaixaRenda faixaMinimaPermitida,
				int prazoEmMeses) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.valorMinimoAplicacao = valorMinimoAplicacao;
		this.valorMaximoAplicacao = valorMaximoAplicacao;
		this.taxaMensalMinima = taxaMensalMinima;
		this.taxaMensalMaxima = taxaMensalMaxima;
		this.faixaMinimaPermitida = faixaMinimaPermitida;
		this.prazoEmMeses = prazoEmMeses;
	}

	//getters
	public long getCodigo() {return codigo;}
	public String getDescricao() {return descricao;}
	public double getValorMinimoAplicacao() {return valorMinimoAplicacao;}
	public double getValorMaximoAplicacao() {return valorMaximoAplicacao;}
	public double getTaxaMensalMinima() {return taxaMensalMinima;}
	public double getTaxaMensalMaxima() {return taxaMensalMaxima;}
	public FaixaRenda getFaixaMinimaPermitida() {return faixaMinimaPermitida;}
	public int getprazoEmMeses() {return prazoEmMeses;}
	
	//setters
	public void setCodigo(long codigo) {this.codigo = codigo;}
	public void setDescricao(String descricao) {this.descricao = descricao;}
	public void setValorMinimoAplicacao(double valorMinimoAplicacao) {this.valorMinimoAplicacao = valorMinimoAplicacao;}
	public void setValorMaximoAplicacao(double valorMaximoAplicacao) {this.valorMaximoAplicacao = valorMaximoAplicacao;}
	public void setTaxaMensalMinima(double taxaMensalMinima) {this.taxaMensalMinima = taxaMensalMinima;}
	public void setTaxaMensalMaxima(double taxaMensalMaxima) {this.taxaMensalMaxima = taxaMensalMaxima;}
	public void setFaixaMinimaPermitida(FaixaRenda faixaRenda) {this.faixaMinimaPermitida = faixaRenda;}
	public void getPrazoEmMeses(int prazoEmMeses) {this.prazoEmMeses = prazoEmMeses;}
}