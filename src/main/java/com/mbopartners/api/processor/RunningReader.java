package com.mbopartners.api.processor;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class RunningReader implements ItemReader<String> {

    private int i = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        System.out.println("Read");
        if (i == 0) {
            i++;
            return "Alphabet";
        } else {
            i = 0;
            return null;
        }
    }
}
