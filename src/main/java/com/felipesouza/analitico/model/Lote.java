package com.felipesouza.analitico.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Lote implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<Cliente> clientes;
	
	private List<Vendedor> vendedores;
	
	private List<Venda> vendas;
	
	private Vendedor piorVendedor;
	
	private Venda vendaMaisCara;
	
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public void setVendedores(List<Vendedor> vendedores) {
		this.vendedores = vendedores;
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}
	
	
	public void calculaPiorVendedor() {
		for (Vendedor vendedor : vendedores) {
			vendedor.calculaValorVenda();
			if(piorVendedor == null || vendedor.getValorVendido() < piorVendedor.getValorVendido()) {
				piorVendedor = vendedor;
			}
		}
	}
	
	public void calculaMaiorCompra() {
		for (Venda venda : vendas) {
			venda.calculaValorTotalVenda();
			if(vendaMaisCara == null || venda.getValorTotalVenda() > vendaMaisCara.getValorTotalVenda()) {
				vendaMaisCara = venda;
			}
		}
	}
	
	public void criaVendas() {
		for(Venda venda : this.vendas) {
			var vendedor = new Vendedor();
			for(Vendedor v : vendedores) {
				if(v.getNome().equals(venda.getNomeVendedor())) {
					vendedor = v;
					break;
				}
			}
			
			String vendaStringTemp = venda.getProdutos().replace("[", "").replaceAll("]", "");
			List<String> vendaString = Arrays.asList(vendaStringTemp.split(","));
			for(String s : vendaString) {
				List<String> pString = Arrays.asList(s.split("-"));
				var p  = new Produto();
				p.setIdProduto(Integer.valueOf(pString.get(0)));
				p.setQuantidade(Integer.valueOf(pString.get(1)));
				p.setValor(Double.valueOf(pString.get(2)));
				venda.getListaProduto().add(p);
			}
			venda.setProdutos("");
			vendedor.getVendas().add(venda);
		}
	}
	
	public Vendedor getPiorVendedor() {
		return piorVendedor;
	}

	public void setPiorVendedor(Vendedor piorVendedor) {
		this.piorVendedor = piorVendedor;
	}

	public Venda getVendaMaisCara() {
		return vendaMaisCara;
	}

	public void setVendaMaisCara(Venda vendaMaisCara) {
		this.vendaMaisCara = vendaMaisCara;
	}

	@Override
	public String toString() {
		return "Lote [clientes=" + clientes + ", vendedores=" + vendedores + ", piorVendedor=" + piorVendedor
				+ ", vendaMaisCara=" + vendaMaisCara + "]";
	}
	
	

}
