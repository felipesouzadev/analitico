package com.felipesouza.analitico.batch.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.felipesouza.analitico.batch.processor.LoteProcessor;
import com.felipesouza.analitico.batch.reader.RegistroReader;
import com.felipesouza.analitico.batch.step.CriaArquivoStep;
import com.felipesouza.analitico.batch.writer.LoteWriter;
import com.felipesouza.analitico.model.Lote;
import com.felipesouza.analitico.model.Registro;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
	private JobBuilderFactory jbf;
	
	@Autowired
	private	StepBuilderFactory sbf;
	
	@Autowired
	private LoteProcessor loteProcessor;
	
	@Autowired
	private RegistroReader registroReader;
	
	@Autowired
	private CriaArquivoStep criaArquivoStep;
	
	@Autowired
	private LoteWriter	loteWriter;


	@Bean
	public Job analisaLote() {
		return jbf.get("analisaLote")
				.incrementer(new RunIdIncrementer())
				.start(lerLoteStep())
				.next(sbf.get("criaAquivoStep")
				 .tasklet(criaArquivoStep)
				 .build())
				 .build();
	}
	
	@Bean
	public Step lerLoteStep() {
		return sbf.get("lerLote")
				.<Registro,Lote> chunk(10)
				.reader(registroReader)
				.processor(loteProcessor)
				.writer(loteWriter)
				.build();
	}
	
}
