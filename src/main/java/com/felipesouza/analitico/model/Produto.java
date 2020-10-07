package com.felipesouza.analitico.model;

import java.io.Serializable;

public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer idProduto;
	
	private Integer quantidade;
	
	private Double valor;

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}
