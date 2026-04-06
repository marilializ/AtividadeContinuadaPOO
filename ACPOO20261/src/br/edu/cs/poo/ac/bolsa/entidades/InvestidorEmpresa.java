package br.edu.cs.poo.ac.bolsa.entidades;
import java.math.BigDecimal;
import java.time.LocalDate;

public class InvestidorEmpresa extends Investidor{
	private String cnpj;
	private double faturamento;
	
	public InvestidorEmpresa(String cnpj, double faturamento, String nome, Endereco endereco, LocalDate dataAbertura, BigDecimal bonus, Contatos contatos) {
		super(nome, endereco, dataAbertura, bonus, contatos);
		this.cnpj = cnpj;
		this.faturamento = faturamento;
	}

	//getters
	public String getCNPJ() {
		return cnpj;
	}
	public double getFaturamento() {
		return faturamento;
	}
	public LocalDate getDataAbertura() {
		return getDataCriacao();
	}
	
	//setters
	public void setCNPJ(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setFaturamento(double faturamento) {
		this.faturamento = faturamento;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		setDataCriacao(dataAbertura);
	}

}
