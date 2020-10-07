package com.felipesouza.analitico.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Registro implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	private String cpf;
	
	private String nome;
	
	private Double salario;
	
	private List<Venda> vendas = new ArrayList<>();
	
	private Double valorVendido;
	
	public void calculaValorVenda() {
		Double soma = (double) 0;
		for(Venda venda : vendas) {
			soma+= (double) venda.getValorTotalVenda();
		}
		
		this.valorVendido =  soma;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public Double getValorVendido() {
		return valorVendido;
	}

	public void setValorVendido(Double valorVendido) {
		this.valorVendido = valorVendido;
	}
	
}
