package com.mbopartners.api.config;

import com.mbopartners.api.processor.MyCustomProcessor;
import com.mbopartners.api.processor.MyCustomReader;
import com.mbopartners.api.processor.MyCustomWriter;
import com.mbopartners.api.processor.RunningProcessor;
import com.mbopartners.api.processor.RunningReader;
import com.mbopartners.api.processor.RunningWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

@Configuration
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Order(1)
    @Bean
    public Job processJob(Step createStep) {
        System.out.println("ProcessJob Method");
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(createStep)
                .end()
                .build();
    }

    @Bean
    public Step createStep(@Qualifier("MyCustomProcessor") ItemProcessor<String, String> processor) {
        System.out.println("CreateStep Method");
        return stepBuilderFactory.get("createStep").<String, String> chunk(1)
                .reader(new MyCustomReader()).processor(processor)
                .writer(new MyCustomWriter()).build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompletionListener();
    }


    @Order(2)
    @Bean
    public Job runningTest(Step runningStep) {
        System.out.println("runningTest Method");
        return jobBuilderFactory.get("runningTest")
                .incrementer(new RunIdIncrementer())
                .start(runningStep)
                //.next(runningNextStep)
                .build();
    }


    @Bean
    public Step runningStep(@Qualifier("RunningProcessor") ItemProcessor<String, String> processor) {
        System.out.println("runningStep Method");
        return stepBuilderFactory.get("runningStep").<String, String> chunk(1)
                .reader(new RunningReader()).processor(processor)
                .writer(new RunningWriter()).build();
    }

}
