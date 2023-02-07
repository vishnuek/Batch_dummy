package com.mbopartners.api.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component("RunningReader")
public class RunningReader implements ItemReader<String> {


    private String previousId;

    public RunningReader( @Value("#{jobParameters['previousJobId']}") String previousId) {
        this.previousId = previousId;
    }

    private int i = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Read " + previousId);
        if (i == 0) {
            i++;
            return "Alphabet";
        } else {
            i = 0;
            return null;
        }
    }
}
