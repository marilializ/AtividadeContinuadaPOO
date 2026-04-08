package br.edu.cs.poo.ac.bolsa.util;

public class ValidadorCpfCnpj {
	private static int calcularDV(String base, int[] pesos) {
	    int soma=0;
	    for (int i=0; i<pesos.length; i++) soma += (base.charAt(i) -'0') * pesos[i];
	    int resto = soma % 11;
	    return (resto<2) ? 0 : 11-resto;
	}

    public static ResultadoValidacao validarCpf(String cpf) {
    	if (cpf == null || cpf.isEmpty()) return ResultadoValidacao.NAO_INFORMADO;
    	cpf = cpf.replaceAll("[^0-9]", "");
        
        if (cpf.length() != 11) return ResultadoValidacao.FORMATO_INVALIDO;
        
        if (cpf.matches("(\\d)\\1{10}")) return ResultadoValidacao.FORMATO_INVALIDO;
        
        int dv1 = calcularDV(cpf.substring(0, 9), new int[]{10,9,8,7,6,5,4,3,2});
        int dv2 = calcularDV(cpf.substring(0, 9) + dv1, new int[]{11,10,9,8,7,6,5,4,3,2});

        if (!cpf.equals(cpf.substring(0, 9) + dv1 + dv2)) return ResultadoValidacao.DV_INVALIDO;
    	
    	return null;
    }

    public static ResultadoValidacao validarCnpj(String cnpj) {
        if (cnpj == null || cnpj.isEmpty()) return ResultadoValidacao.NAO_INFORMADO;
        
        cnpj = cnpj.replaceAll("[^0-9]", "");
        
        if (cnpj.length() != 14) return ResultadoValidacao.FORMATO_INVALIDO;
        
        if (cnpj.matches("(\\d)\\1{13}")) return ResultadoValidacao.FORMATO_INVALIDO;
        
        int dv1 = calcularDV(cnpj.substring(0, 12), new int[]{5,4,3,2,9,8,7,6,5,4,3,2});
        int dv2 = calcularDV(cnpj.substring(0, 12) + dv1, new int[]{6,5,4,3,2,9,8,7,6,5,4,3,2});
        
        if (!cnpj.equals(cnpj.substring(0, 12) + dv1 + dv2)) return ResultadoValidacao.DV_INVALIDO;
        
        return null;
    }
}