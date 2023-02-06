package com.mbopartners.api.processor;

import org.springframework.batch.item.ItemWriter;

import java.util.ArrayList;
import java.util.List;

public class MyCustomWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> list) throws Exception {
        System.out.println("file generated->" + list);
    }
}
