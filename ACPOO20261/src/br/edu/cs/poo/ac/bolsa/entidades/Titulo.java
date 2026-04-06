package br.edu.cs.poo.ac.bolsa.entidades;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Titulo{
	private InvestidorPessoa investidorPessoa;
	private InvestidorEmpresa investidorEmpresa;
	private Ativo ativo;
	private BigDecimal valorInvestido;
	private BigDecimal valorAtual;
	private BigDecimal taxaDiaria;
	private LocalDate dataAplicacao;
	private LocalDate dataVencimento;
	private LocalDate dataUltimoRendimento;
	private StatusTitulo status;
	
	public Titulo(InvestidorPessoa investidorPessoa, InvestidorEmpresa investidorEmpresa, Ativo ativo,
					BigDecimal valorInvestido, BigDecimal valorAtual, BigDecimal taxaDiaria,
					LocalDate dataAplicacao, LocalDate dataVencimento, LocalDate dataUltimoRendimento,
					StatusTitulo status) {
		this.investidorPessoa = investidorPessoa;
		this.investidorEmpresa = investidorEmpresa;
		this.ativo = ativo;
		this.valorInvestido = valorInvestido;
		this.valorAtual = valorAtual;
		this.taxaDiaria = taxaDiaria;
		this.dataAplicacao = dataAplicacao;
		this.dataVencimento = dataVencimento;
		this.dataUltimoRendimento = dataUltimoRendimento;
		this.status = status;
	}
	
	//getters
	public InvestidorPessoa getInvestidorPessoa() {return investidorPessoa;}
	public InvestidorEmpresa getInvestidorEmpresa() {return investidorEmpresa;}
	public Ativo getAtivo() {return ativo;}
	public BigDecimal getValorInvestido() {return valorInvestido;}
	public BigDecimal getValorAtual() {return valorAtual;}
	public BigDecimal getTaxaDiaria() {return taxaDiaria;}
	public LocalDate getDataAplicacao() {return dataAplicacao;}
	public LocalDate getDataVencimento() {return dataVencimento;}
	public LocalDate getDataUltimoRendimento() {return dataUltimoRendimento;}
	public StatusTitulo getStatus() {return status;}
	
	//setters
	public void setInvestidorPessoa(InvestidorPessoa investidorPessoa) {this.investidorPessoa = investidorPessoa;}
	public void setInvestidorEmpresa(InvestidorEmpresa investidorEmpresa) {this.investidorEmpresa = investidorEmpresa;}
	public void setAtivo(Ativo ativo) {this.ativo = ativo;}
	public void setValorInvestido(BigDecimal valorInvestido) {this.valorInvestido = valorInvestido;}
	public void setValorAtual(BigDecimal valorAtual) {this.valorAtual = valorAtual;}
	public void setTaxaDiaria(BigDecimal taxaDiaria) {this.taxaDiaria = taxaDiaria;}
	public void setDataAplicacao(LocalDate dataAplicacao) {this.dataAplicacao = dataAplicacao;}
	public void setDataVencimento(LocalDate dataVencimento) {this.dataVencimento = dataVencimento;}
	public void setDataUltimoRendimento(LocalDate dataUltimoRendimento) {this.dataUltimoRendimento = dataUltimoRendimento;}
	public void setStatus(StatusTitulo status) {this.status = status;}
	
	//other methods
	public boolean render() {
		LocalDate hoje = LocalDate.now();
		
		if (status != StatusTitulo.ATIVO) return false;
		if (!hoje.isBefore(dataVencimento)) return false;
		if (!hoje.isAfter(dataAplicacao)) return false;
		if (dataUltimoRendimento != null && !hoje.isAfter(dataUltimoRendimento)) return false;
		
		long dias;
			
		if (dataUltimoRendimento == null) {dias = ChronoUnit.DAYS.between(dataAplicacao, hoje);}
		else {dias = ChronoUnit.DAYS.between(dataUltimoRendimento, hoje);}
			
		BigDecimal taxaDiariaSobre100 = taxaDiaria.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
		BigDecimal taxaDiariaSobre100Mais1 = taxaDiariaSobre100.add(BigDecimal.valueOf(1));
		BigDecimal taxaDiariaSobre100Mais1ElevDD = taxaDiariaSobre100Mais1.pow((int)dias);
		valorAtual = valorAtual.multiply(taxaDiariaSobre100Mais1ElevDD);
		
		dataUltimoRendimento = hoje;
		return true;
	}
	
	public String getNumero() {
		String codigoAtivo = String.valueOf(ativo.getCodigo());
	    String dataFormatada = dataAplicacao.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

	    if (investidorPessoa != null) return "000" + investidorPessoa.getCpf() + codigoAtivo + dataFormatada + "0000";
	    else if (investidorEmpresa != null) return investidorEmpresa.getCnpj() + codigoAtivo + dataFormatada + "0000";
	    return null;
	}
}