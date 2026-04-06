package br.edu.cs.poo.ac.bolsa.entidades;
import java.math.BigDecimal;
import java.time.LocalDate;

public class InvestidorPessoa extends Investidor {
	private String cpf;
	private double renda;
	private FaixaRenda faixaRenda;
	
	public InvestidorPessoa(String cpf, double renda, FaixaRenda faixaRenda, String nome, Endereco endereco, LocalDate dataNascimento, BigDecimal bonus, Contatos contatos) {
		super(nome, endereco, dataNascimento, bonus, contatos);
		this.cpf = cpf;
		this.renda = renda;
		this.faixaRenda = faixaRenda;
	}
	
	//getters
	public String getCPF() {
		return cpf;
	}
	public double getRenda() {
		return renda;
	}
	public FaixaRenda getFaixaRenda() {
		return faixaRenda;
	}
	public LocalDate getDataNascimento() {
		return getDataCriacao();
	}
	
	//setters
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	public void setRenda(double renda) {
		this.renda = renda;
	}
	public void setFaixaRenda(FaixaRenda faixaRenda) {
		this.faixaRenda = faixaRenda;
	}
	public void setDataNascimento(LocalDate dataNascimento) {
		setDataCriacao(dataNascimento);
	}
	
}
