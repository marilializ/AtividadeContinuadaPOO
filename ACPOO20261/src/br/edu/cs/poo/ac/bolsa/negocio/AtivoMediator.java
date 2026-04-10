package br.edu.cs.poo.ac.bolsa.negocio;

import br.edu.cs.poo.ac.bolsa.dao.DAOAtivo;
import br.edu.cs.poo.ac.bolsa.entidade.Ativo;
import br.edu.cs.poo.ac.bolsa.util.MensagensValidacao;

public class AtivoMediator {
	private DAOAtivo dao = new DAOAtivo();
	
	private MensagensValidacao validar(Ativo ativo) {
	    MensagensValidacao msgs = new MensagensValidacao();

	    // ativo não pode ser null
	    if (ativo == null) {
	        msgs.adicionar("Ativo não pode ser nulo.");
	        return msgs;
	    }

	    // código
	    if (ativo.getCodigo() <= 0) {
	        msgs.adicionar("Código deve ser maior que zero.");
	    }

	    // descrição
	    if (ativo.getDescricao() == null || ativo.getDescricao().trim().isEmpty()) {
	        msgs.adicionar("Descrição é obrigatória.");
	    }

	    // valor mínimo
	    if (ativo.getValorMinimoAplicacao() <= 0) {
	        msgs.adicionar("Valor mínimo deve ser maior que zero.");
	    }

	    // valor máximo
	    if (ativo.getValorMaximoAplicacao() <= 0) {
	        msgs.adicionar("Valor máximo deve ser maior que zero.");
	    }

	    // relação min <= max
	    if (ativo.getValorMinimoAplicacao() > ativo.getValorMaximoAplicacao()) {
	        msgs.adicionar("Valor mínimo não pode ser maior que o valor máximo.");
	    }

	    // taxa mínima
	    if (ativo.getTaxaMensalMinima() < 0) {
	        msgs.adicionar("Taxa mínima deve ser maior ou igual a zero.");
	    }

	    // taxa máxima
	    if (ativo.getTaxaMensalMaxima() < 0) {
	        msgs.adicionar("Taxa máxima deve ser maior ou igual a zero.");
	    }

	    // relação taxa min <= max
	    if (ativo.getTaxaMensalMinima() > ativo.getTaxaMensalMaxima()) {
	        msgs.adicionar("Taxa mínima não pode ser maior que a taxa máxima.");
	    }

	    // faixa renda
	    if (ativo.getFaixaMinimaPermitida() == null) {
	        msgs.adicionar("Faixa de renda é obrigatória.");
	    }

	    // prazo
	    if (ativo.getprazoEmMeses() <= 0) {
	        msgs.adicionar("Prazo deve ser maior que zero.");
	    }

	    return msgs;
	}
	
	public MensagensValidacao incluir(Ativo ativo) {
		MensagensValidacao msgs = validar(ativo);
		if (msgs.estaVazio()) {
			boolean sucesso = dao.incluir(ativo);
			
			if (!sucesso) {
				 msgs.adicionar("Ativo já existente.");
			}
	}
		return msgs;
	}
		
		public MensagensValidacao alterar(Ativo ativo) {
			MensagensValidacao msgs = validar(ativo);
			
			if (msgs.estaVazio()) {
				boolean sucesso = dao.alterar(ativo);
				
				if (!sucesso) {
					 msgs.adicionar("Ativo não existente.");
				}	

		}
			return msgs;
		}
			
			
		public MensagensValidacao excluir(long codigo) {
			MensagensValidacao msgs = new MensagensValidacao();
			
			if (codigo <= 0) {
		        msgs.adicionar("Código deve ser maior que zero.");
		    }
			
			if (msgs.estaVazio()) {
				boolean sucesso = dao.excluir(codigo);
				
				if (!sucesso) {
					 msgs.adicionar("Ativo não existente.");
				}	
			}
			
			return msgs;

		}
		
		public Ativo buscar(long codigo) {
			if (codigo <= 0) {
		        return null;
		    }else {
		    	return dao.buscar(codigo);
		    }
		}
}
