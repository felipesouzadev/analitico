package com.felipesouza.analitico.batch.config;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@EnableScheduling
@Component
public class JobScheduler {
	
	@Autowired
	Job analisaJob;
	
	@Autowired
	JobLauncher jobLauncher;

	private String path = System.getProperty("user.home");
	
	private ResourcePatternResolver patternResolver;
	
	public JobScheduler() {
		patternResolver = new PathMatchingResourcePatternResolver();
	}

	@Scheduled(fixedRate = 120000)
	public void perform() throws IOException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {		
		Resource[] resources = patternResolver.getResources("file:/"+path+"/data/in/*.txt");

		if(ObjectUtils.isEmpty(resources)) {
			return;
		}

		JobParameters params = new JobParametersBuilder()
				.addString("resourceEntrada", resources[0].getFile().getPath())
				.toJobParameters();

		jobLauncher.run(analisaJob , params);
	}
}
