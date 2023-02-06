package com.mbopartners.api;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration
		//(exclude={DataSourceAutoConfiguration.class})
@EnableFeignClients
@EnableBatchProcessing
@EnableScheduling
public class JobdivaPwCInvoiceGenerationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobdivaPwCInvoiceGenerationApiApplication.class, args);
	}
}
