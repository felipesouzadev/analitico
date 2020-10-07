package com.felipesouza.analitico.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Venda extends Registro implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Integer idVenda;
	
	private List<Produto> listaProduto = new ArrayList<>();
	
	private String produtos;
	
	private String nomeVendedor;
	
	private Double valorTotalVenda;

	public Integer getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}

	public String getProdutos() {
		return produtos;
	}

	public void setProdutos(String produtos) {
		this.produtos = produtos;
	}

	public String getNomeVendedor() {
		return nomeVendedor;
	}

	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
	
	public Double getValorTotalVenda() {
		return valorTotalVenda;
	}

	public void setValorTotalVenda(Double valorTotalVenda) {
		this.valorTotalVenda = valorTotalVenda;
	}

	public void calculaValorTotalVenda() {
		Double soma = (double) 0;
		for (Produto produto : listaProduto) {
			soma += (double)produto.getQuantidade() * produto.getValor();
		}
		
		this.valorTotalVenda =  soma;
	}
	
}
