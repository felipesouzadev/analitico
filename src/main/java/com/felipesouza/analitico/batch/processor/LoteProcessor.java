package com.felipesouza.analitico.batch.processor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.felipesouza.analitico.model.Cliente;
import com.felipesouza.analitico.model.Lote;
import com.felipesouza.analitico.model.Registro;
import com.felipesouza.analitico.model.Venda;
import com.felipesouza.analitico.model.Vendedor;

@Component
@StepScope
public class LoteProcessor implements ItemProcessor<Registro, Lote>, StepExecutionListener{

	private List<Vendedor> vendedores = new ArrayList<>();
	
	private List<Cliente> clientes = new ArrayList<>();
	
	private List<Venda>	vendas = new ArrayList<>();

	@Override
	public Lote process(Registro item) throws Exception {
		if(item.getClass().equals(Vendedor.class)) {
			vendedores.add((Vendedor)item);		
		}else if(item.getClass().equals(Cliente.class)) {
			clientes.add((Cliente) item);
		}else {
			vendas.add((Venda) item);
		}
		return null;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// to implement
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.getJobExecution().getExecutionContext().put("LOTE", criaArquivoLote());
		return ExitStatus.COMPLETED;
	}
	
	private Lote criaArquivoLote() {
		var lote = new Lote();
		lote.setVendedores(this.vendedores);
		lote.setClientes(this.clientes);
		lote.setVendas(this.vendas);
		lote.criaVendas();
		lote.calculaMaiorCompra();
		lote.calculaPiorVendedor();
		return lote;
		
	}
}
