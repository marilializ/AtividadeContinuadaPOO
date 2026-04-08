package br.edu.cs.poo.ac.bolsa.entidade;
import java.math.BigDecimal;
import java.time.LocalDate;

public class InvestidorPessoa extends Investidor {
	private static final long serialVersionUID = 1L;
	
	private String cpf;
	private double renda;
	private FaixaRenda faixaRenda;

	public InvestidorPessoa() {}
	public InvestidorPessoa(String nome, Contatos contatos, Endereco endereco, BigDecimal bonus, LocalDate dataNascimento, String cpf, double renda, FaixaRenda faixaRenda) {
		super(nome, endereco, dataNascimento, bonus, contatos);
		this.cpf = cpf;
		this.renda = renda;
		this.faixaRenda = faixaRenda;
	}
	
	//getters
	public String getCpf() {return cpf;}
	public double getRenda() {return renda;}
	public FaixaRenda getFaixaRenda() {return faixaRenda;}
	public LocalDate getDataNascimento() {return getDataCriacao();}
	
	//setters
	public void setCpf(String cpf) {this.cpf = cpf;}
	public void setRenda(double renda) {this.renda = renda;}
	public void setFaixaRenda(FaixaRenda faixaRenda) {this.faixaRenda = faixaRenda;}
	public void setDataNascimento(LocalDate dataNascimento) {setDataCriacao(dataNascimento);}
}
