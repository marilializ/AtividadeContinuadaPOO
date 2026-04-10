package br.edu.cs.poo.ac.bolsa.negocio;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.edu.cs.poo.ac.bolsa.dao.DAOInvestidorEmpresa;
import br.edu.cs.poo.ac.bolsa.dao.DAOInvestidorPessoa;
import br.edu.cs.poo.ac.bolsa.entidade.Contatos;
import br.edu.cs.poo.ac.bolsa.entidade.Endereco;
import br.edu.cs.poo.ac.bolsa.entidade.FaixaRenda;
import br.edu.cs.poo.ac.bolsa.entidade.Investidor;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorEmpresa;
import br.edu.cs.poo.ac.bolsa.entidade.InvestidorPessoa;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;
import br.edu.cs.poo.ac.bolsa.util.ResultadoValidacao;
import br.edu.cs.poo.ac.bolsa.util.ValidadorCpfCnpj;

public class InvestidorMediator {
	private DAOInvestidorEmpresa daoInvEmp = new DAOInvestidorEmpresa ();
	private DAOInvestidorPessoa daoInvPes = new DAOInvestidorPessoa();
		
	private MensagensValidacao validarEndereco(Endereco endereco) {
	    MensagensValidacao msgs = new MensagensValidacao();

	    if (endereco.getLogradouro() == null || endereco.getLogradouro().trim().isEmpty())
	        msgs.adicionar("Logradouro é obrigatório.");

	    if (endereco.getNumero() == null || endereco.getNumero().trim().isEmpty())
	        msgs.adicionar("Número é obrigatório.");

	    if (endereco.getPais() == null || endereco.getPais().trim().isEmpty())
	        msgs.adicionar("País é obrigatório.");

	    if (endereco.getEstado() == null || endereco.getEstado().trim().isEmpty())
	        msgs.adicionar("Estado é obrigatório.");

	    if (endereco.getCidade() == null || endereco.getCidade().trim().isEmpty())
	        msgs.adicionar("Cidade é obrigatório.");

	    return msgs;
	}
	
	private MensagensValidacao validarContatos(Contatos contatos, boolean ehPessoaJuridica) {
	    MensagensValidacao msgs = new MensagensValidacao();

	    // email
	    if (contatos.getEmail() == null ||
	    	contatos.getEmail().trim().isEmpty() ||
	    	!contatos.getEmail().contains("@")) {
	    	msgs.adicionar("E-mail inválido.");}

	    // telefones
	    boolean temTelefone = false;

	    if (contatos.getTelefoneFixo() != null && !contatos.getTelefoneFixo().isEmpty()) {
	        temTelefone = true;
	        if (!contatos.getTelefoneFixo().matches("\\d+")) {
	            msgs.adicionar("Telefone fixo inválido.");}
	    }
	    if (contatos.getTelefoneCelular() != null && !contatos.getTelefoneCelular().isEmpty()) {
	        temTelefone = true;
	        if (!contatos.getTelefoneCelular().matches("\\d+")) {
	            msgs.adicionar("Telefone celular deve conter apenas números.");}
	    }
	    if (contatos.getNumeroWhatsApp() != null && !contatos.getNumeroWhatsApp().isEmpty()) {
	        temTelefone = true;
	        if (!contatos.getNumeroWhatsApp().matches("\\d+")) {
	            msgs.adicionar("WhatsApp inválido.");}
	    }
	    if (!temTelefone) msgs.adicionar("Pelo menos um telefone deve ser informado.");
	    if (ehPessoaJuridica) {
	        if (contatos.getNomeParaContato() == null ||
	        	contatos.getNomeParaContato().trim().isEmpty()) {
	            msgs.adicionar("Nome para contato é obrigatório para pessoa jurídica.");}
	    }

	    return msgs;
	}
	
	private MensagensValidacao validar(DadosInvestidor dadosInv) {
	    MensagensValidacao msgs = new MensagensValidacao();

	    if (dadosInv == null) {
	        msgs.adicionar("Dados do investidor não podem ser nulos.");
	        return msgs;
	    }

	    if (dadosInv.getNome() == null || dadosInv.getNome().trim().isEmpty())
	        msgs.adicionar("Nome é obrigatório.");

	    if (dadosInv.getEndereco() == null) {
	        msgs.adicionar("Endereço é obrigatório.");
	    } else {
	        MensagensValidacao m = validarEndereco(dadosInv.getEndereco());
	        for (String s : m.getMensagens()) msgs.adicionar(s);
	    }

	    if (dadosInv.getDataCriacao() == null || dadosInv.getDataCriacao().isAfter(java.time.LocalDate.now()))
	        msgs.adicionar("Data inválida.");

	    if (dadosInv.getBonus() == null || dadosInv.getBonus().doubleValue() < 0)
	        msgs.adicionar("Bônus inválido.");

	    if (dadosInv.getContatos() == null) {
	        msgs.adicionar("Contatos é obrigatório.");
	    } else {
	        MensagensValidacao m = validarContatos(dadosInv.getContatos(), dadosInv.ehInvestidorEmpresa());
	        for (String s : m.getMensagens()) msgs.adicionar(s);
	    }

	    return msgs;
	}
	
	private MensagensValidacao validarInvestidorEmpresa(InvestidorEmpresa ie) {
	    MensagensValidacao msgs = new MensagensValidacao();

	    DadosInvestidor dados = new DadosInvestidor(ie, null);
	    msgs = validar(dados);

	    ResultadoValidacao res = ValidadorCpfCnpj.validarCnpj(ie.getCnpj());
	    if (res != null) msgs.adicionar("CNPJ inválido.");

	    if (ie.getFaturamento() < 100000.0) msgs.adicionar("Faturamento deve ser maior ou igual a 100000.0.");

	    return msgs;
	}
	
	private MensagensValidacao validarInvestidorPessoa(InvestidorPessoa ip) {
	    MensagensValidacao msgs = new MensagensValidacao();

	    DadosInvestidor dados = new DadosInvestidor(null, ip);
	    msgs = validar(dados);

	    ResultadoValidacao res = ValidadorCpfCnpj.validarCpf(ip.getCpf());
	    if (res != null) msgs.adicionar("CPF inválido.");

	    if (ip.getRenda() < 10000.0) msgs.adicionar("Renda deve ser maior ou igual a 10000.0.");

	    // SETAR FAIXA
	    double renda = ip.getRenda();

	    if (renda >= 300000.01) ip.setFaixaRenda(FaixaRenda.PREMIUM);
	    else if (renda >= 5000.01) ip.setFaixaRenda(FaixaRenda.DIFERENCIADA);
	    else ip.setFaixaRenda(FaixaRenda.REGULAR);

	    return msgs;
	}
	
	public MensagensValidacao incluirInvestidorEmpresa(InvestidorEmpresa ie) {
	    MensagensValidacao msgs = validarInvestidorEmpresa(ie);

	    if (msgs.estaVazio()) {
	        if (!daoInvEmp.incluir(ie)) {
	            msgs.adicionar("Investidor Empresa já existente.");
	        }
	    }

	    return msgs;
	}

	public MensagensValidacao alterarInvestidorEmpresa(InvestidorEmpresa ie) {
	    MensagensValidacao msgs = validarInvestidorEmpresa(ie);

	    if (msgs.estaVazio()) {
	        if (!daoInvEmp.alterar(ie)) {
	            msgs.adicionar("Investidor Empresa não existente.");
	        }
	    }

	    return msgs;
	}

	public MensagensValidacao excluirInvestidorEmpresa(String cnpj) {
	    MensagensValidacao msgs = new MensagensValidacao();

	    if (ValidadorCpfCnpj.validarCnpj(cnpj) != null) {
	        msgs.adicionar("CNPJ inválido.");
	        return msgs;
	    }

	    if (!daoInvEmp.excluir(cnpj)) {
	        msgs.adicionar("Investidor Empresa não existente.");
	    }

	    return msgs;
	}

	public InvestidorEmpresa buscarInvestidorEmpresa(String cnpj) {
	    if (ValidadorCpfCnpj.validarCnpj(cnpj) != null) {
	        return null;
	    }

	    return daoInvEmp.buscar(cnpj);
	}
	
	public MensagensValidacao incluirInvestidorPessoa(InvestidorPessoa ip) {
	    MensagensValidacao msgs = validarInvestidorPessoa(ip);

	    if (msgs.estaVazio()) {
	        if (!daoInvPes.incluir(ip)) {
	            msgs.adicionar("Investidor Pessoa já existente.");
	        }
	    }

	    return msgs;
	}

	public MensagensValidacao alterarInvestidorPessoa(InvestidorPessoa ip) {
	    MensagensValidacao msgs = validarInvestidorPessoa(ip);

	    if (msgs.estaVazio()) {
	        if (!daoInvPes.alterar(ip)) {
	            msgs.adicionar("Investidor Pessoa não existente.");
	        }
	    }

	    return msgs;
	}

	public MensagensValidacao excluirInvestidorPessoa(String cpf) {
	    MensagensValidacao msgs = new MensagensValidacao();

	    if (ValidadorCpfCnpj.validarCpf(cpf) != null) {
	        msgs.adicionar("CPF inválido.");
	        return msgs;
	    }

	    if (!daoInvPes.excluir(cpf)) {
	        msgs.adicionar("Investidor Pessoa não existente.");
	    }

	    return msgs;
	}

	public InvestidorPessoa buscarInvestidorPessoa(String cpf) {
	    if (ValidadorCpfCnpj.validarCpf(cpf) != null) {
	        return null;
	    }

	    return daoInvPes.buscar(cpf);
	}
	
}
	
	