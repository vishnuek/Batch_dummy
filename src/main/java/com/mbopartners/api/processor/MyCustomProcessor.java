package com.mbopartners.api.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component("MyCustomProcessor")
public class MyCustomProcessor implements ItemProcessor<String, String> {

    private Long jobId;

    public MyCustomProcessor( @Value("#{stepExecution.jobExecution.id}") Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public String process(String s) throws Exception {
        System.out.println("JobId is " + this.jobId);
        return s.toLowerCase();
    }
}
