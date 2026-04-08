package br.edu.cs.poo.ac.bolsa.util;

public class ValidadorCpfCnpj {

    public static ResultadoValidacao validarCpf(String cpf) {
        return null;
    }

    public static ResultadoValidacao validarCnpj(String cnpj) {

        if (cnpj == null || cnpj.isEmpty()) {
            return ResultadoValidacao.NAO_INFORMADO;
        }
        if (cnpj.length() < 14) {
        	return ResultadoValidacao.FORMATO_INVALIDO;
        }
        cnpj = cnpj.replaceAll("[^0-9]", "");
        if (cnpj.matches("(\\d)\\1{13}")) {
            return ResultadoValidacao.FORMATO_INVALIDO;
        }
        
        return null;
    }
}