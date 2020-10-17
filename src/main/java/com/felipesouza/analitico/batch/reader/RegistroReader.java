package com.felipesouza.analitico.batch.reader;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.felipesouza.analitico.model.Cliente;
import com.felipesouza.analitico.model.Registro;
import com.felipesouza.analitico.model.Venda;
import com.felipesouza.analitico.model.Vendedor;

@Component
@StepScope
public class RegistroReader extends FlatFileItemReader<Registro> implements StepExecutionListener{
	
	private String pathIn;
	private String pathOut;

	public RegistroReader(@Value("#{jobParameters[resourceEntrada]}")String pathIn) {
		setResource(new FileSystemResource(pathIn));
		setName("leitorDeRegistro");
		setLineMapper(lineMapper());
		this.pathIn = pathIn;
		this.pathOut = pathIn.replace("\\data\\in", "\\data\\out");
	}
	
	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		stepExecution.getJobExecution().getExecutionContext().put("PATHIN", pathIn);
		stepExecution.getJobExecution().getExecutionContext().put("PATHOUT", pathOut);
		return ExitStatus.COMPLETED;
	}
	
	public PatternMatchingCompositeLineMapper<Registro> lineMapper(){
		PatternMatchingCompositeLineMapper<Registro> lineMapper = new PatternMatchingCompositeLineMapper<>();
		
		Map<String, LineTokenizer> tokenizers = new HashMap<>(3);
		
		tokenizers.put("001*", vendedorTokenizer());
		tokenizers.put("002*", clienteTokenizer());
		tokenizers.put("003*", vendaTokenizer());
		
		lineMapper.setTokenizers(tokenizers);

		Map<String, FieldSetMapper<Registro>> mappers = new HashMap<>(3);
		
		mappers.put("001*", vendedorMapper());
		mappers.put("002*", clienteMapper());
		mappers.put("003*", vendaMapper());
		
		lineMapper.setFieldSetMappers(mappers);
	
		return lineMapper;
	}
	
	public LineTokenizer vendedorTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter("ç");
		tokenizer.setNames("cpf", "nome", "salario");
		tokenizer.setIncludedFields(1,2,3);
		return tokenizer;
	}
	
	public LineTokenizer clienteTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter("ç");
		tokenizer.setNames("cnpj", "nome", "areaNegocio");
		tokenizer.setIncludedFields(1,2,3);
		return tokenizer;
	}

	public LineTokenizer vendaTokenizer() {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter("ç");
		tokenizer.setNames("idVenda", "produtos", "nomeVendedor");
		tokenizer.setIncludedFields(1,2,3);
		return tokenizer;
	}
	

	public FieldSetMapper<Registro> vendedorMapper() {
		BeanWrapperFieldSetMapper<Registro> fieldMapper = new BeanWrapperFieldSetMapper<>();
		fieldMapper.setTargetType(Vendedor.class);
		return fieldMapper;
	}
	
	public FieldSetMapper<Registro> clienteMapper() {
		BeanWrapperFieldSetMapper<Registro> fieldMapper = new BeanWrapperFieldSetMapper<>();
		fieldMapper.setTargetType(Cliente.class);
		
		return fieldMapper;
	}

	public FieldSetMapper<Registro> vendaMapper() {
		BeanWrapperFieldSetMapper<Registro> fieldMapper = new BeanWrapperFieldSetMapper<>();
		fieldMapper.setTargetType(Venda.class);
		
		return fieldMapper;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
	}
}