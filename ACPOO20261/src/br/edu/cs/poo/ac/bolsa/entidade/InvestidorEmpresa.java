package br.edu.cs.poo.ac.bolsa.entidade;
import java.math.BigDecimal;
import java.time.LocalDate;

public class InvestidorEmpresa extends Investidor{
	private String cnpj;
	private double faturamento;
	
	public InvestidorEmpresa(String nome, Endereco endereco, LocalDate dataAbertura,
						BigDecimal bonus, Contatos contatos, String cnpj, double faturamento) {
		super(nome, endereco, dataAbertura, bonus, contatos);
		this.cnpj = cnpj;
		this.faturamento = faturamento;
	}

	//getters
	public String getCnpj() {return cnpj;}
	public double getFaturamento() {return faturamento;}
	public LocalDate getDataAbertura() {return getDataCriacao();}
	
	//setters
	public void setCnpj(String cnpj) {this.cnpj = cnpj;}
	public void setFaturamento(double faturamento) {this.faturamento = faturamento;}
	public void setDataAbertura(LocalDate dataAbertura) {setDataCriacao(dataAbertura);}

}
