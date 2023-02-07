package com.mbopartners.api.processor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component("MyCustomReader")
public class MyCustomReader implements ItemReader<String> {


    private String date;

    public MyCustomReader( @Value("#{jobParameters['date']}") String date) {
        this.date = date;
    }

    private String[] messages = { "javainuse.com",
            "Welcome to Spring Batch Example",
            "We use H2 Database for this example" };

    private int count = 0;

    @Override
    public String read() throws Exception {
        System.out.println("Read" + this.date);
        if (count < messages.length) {
            return messages[count++];
        } else {
            count = 0;
        }
        return null;
    }
}
