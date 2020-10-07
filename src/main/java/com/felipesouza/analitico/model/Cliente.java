package com.felipesouza.analitico.model;

import java.io.Serializable;

public class Cliente extends Registro implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String cnpj;
	
	private String nome;
	
	private String areaNegocio;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAreaNegocio() {
		return areaNegocio;
	}

	public void setAreaNegocio(String areaNegocio) {
		this.areaNegocio = areaNegocio;
	}
	
	
}
