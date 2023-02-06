package com.mbopartners.api.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@StepScope
@Component("RunningProcessor")
public class RunningProcessor implements ItemProcessor<String, String> {

    @Override
    public String process(String s) throws Exception {
        System.out.println("Process");
        return s.toLowerCase();
    }
}
