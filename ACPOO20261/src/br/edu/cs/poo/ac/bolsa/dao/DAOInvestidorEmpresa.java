package br.edu.cs.poo.ac.bolsa.dao;

import br.edu.cs.poo.ac.bolsa.entidade.InvestidorEmpresa;

public class DAOInvestidorEmpresa extends DAOGenerico{
	public DAOInvestidorEmpresa() {
		inicializarCadastro(InvestidorEmpresa.class);
	}
	public InvestidorEmpresa buscar(String cnpj) {
		return (InvestidorEmpresa)cadastro.buscar(cnpj);
	}	
	public boolean incluir(InvestidorEmpresa investidorEmpresa) {		
		if (buscar(investidorEmpresa.getCnpj()) == null) {
			cadastro.incluir(investidorEmpresa, "" + investidorEmpresa.getCnpj());
			return true; 
		} else {
			return false;
		}
	}
	public boolean alterar(InvestidorEmpresa investidorEmpresa) {
		if (buscar(investidorEmpresa.getCnpj()) != null) {
			cadastro.alterar(investidorEmpresa, "" + investidorEmpresa.getCnpj());
			return true; 
		} else {
			return false;
		}
	}
	public boolean excluir(String cnpj) {
		if (buscar(cnpj) != null) {
			cadastro.excluir("" + cnpj);
			return true; 
		} else {
			return false;
		}
	}
}
