package com.felipesouza.analitico.batch.step;

import com.felipesouza.analitico.model.Lote;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
@StepScope
public class CriaArquivoStep implements Tasklet, StepExecutionListener{
	
	private String pathIn;
	private String pathOut;
	private ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext ) throws Exception {
		StepExecution stepExecution = contribution.getStepExecution();
		StringBuilder sb = new StringBuilder();

		Lote lote = (Lote) stepExecution.getJobExecution().getExecutionContext().get("LOTE");
		sb.append("Quantidade de clientes no arquivo de entrada: " + lote.getClientes().size() + "\r\n");
		sb.append("Quantidade de vendedores no arquivo de entrada: " + lote.getVendedores().size() + "\r\n");
		sb.append("ID da venda mais cara: " + lote.getVendaMaisCara().getIdVenda() + "\r\n");
		sb.append("Pior vendedor: "+ lote.getPiorVendedor().getNome());
		
		FileWriter myWriter = new FileWriter(pathOut);
		renomeiaArquivoDeEntrada(pathIn);
		myWriter.write(sb.toString());
		myWriter.close();
		return RepeatStatus.FINISHED;
	}

	private void renomeiaArquivoDeEntrada(String pathIn) throws IOException, IOException {
		Resource resource = patternResolver.getResource("file:/" + pathIn);
		File file = resource.getFile();
		file.renameTo(new File(file.getAbsolutePath().replace(".txt", ".processado")));
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		pathIn = (String) stepExecution.getJobExecution().getExecutionContext().get("PATHIN");
		pathOut = (String) stepExecution.getJobExecution().getExecutionContext().get("PATHOUT");
		
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		return null;
	}

}
