package com.felipesouza.analitico.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.felipesouza.analitico.model.Lote;

@Component
public class LoteWriter implements ItemWriter<Lote>{


	@Override
	public void write(List<? extends Lote> items) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
