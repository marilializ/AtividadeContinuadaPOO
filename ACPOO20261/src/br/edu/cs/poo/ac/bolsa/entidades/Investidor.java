package br.edu.cs.poo.ac.bolsa.entidades;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class Investidor {
	private String nome;
	private Endereco endereco;
	private LocalDate dataCriacao;
	private BigDecimal bonus;
	private Contatos contatos;
	
	public Investidor(String nome, Endereco endereco, LocalDate dataCriacao, BigDecimal bonus, Contatos contatos) {
		this.nome = nome;
		this.endereco = endereco;
		this.dataCriacao = dataCriacao;
		this.bonus = bonus;
		this.contatos = contatos;
	}
	
	//getters
	public String getNome() {
		return nome;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public Contatos getContatos() {
		return contatos;
	}
	public BigDecimal getBonus() {
		return bonus;
	}
	
	protected LocalDate getDataCriacao() {
		return dataCriacao;
	}
	
	public int getIdade() {
        Period periodo = Period.between(dataCriacao, LocalDate.now());
        int idade = periodo.getYears();
        return idade;
}
	
	//setters
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public void setContatos(Contatos contatos) {
		this.contatos = contatos;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	//other methods
	public void creditarBonus(BigDecimal valor) {
		bonus = bonus.add(valor);
	}
	public void debitarBonus(BigDecimal valor) {
		bonus = bonus.subtract(valor);
	}	
	
}
