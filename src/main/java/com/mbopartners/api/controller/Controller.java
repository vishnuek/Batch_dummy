package com.mbopartners.api.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequestMapping("/test")
public class Controller {

    @Autowired
    JobLauncher jobLauncher;

    //@Autowired
    //Job job;

    @Autowired
    List<Job> jobs;


    @Autowired
    @Qualifier("processJob")
    private Job job1;

    @Autowired
    @Qualifier("runningTest")
    private Job job2;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @RequestMapping(value="/value")
    public ResponseEntity<Map<String, String>> test() {
        List<Job> jobs = new ArrayList<>();
        if ("processJob".equals("processJob")) {
            jobs.add(job1);
            jobs.add(job2);
        } else if ("".equals("runningTest")) {
            jobs.add(job2);
        }
        System.out.println("Batch job starting");
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
        //jobLauncher.run(job, jobParameters);
        jobs.forEach(job -> {
            try {
                jobLauncher.run(job, jobParameters);
            } catch (Exception e) {
                //logger.error("Job {} cannot be executed", job.getName());
                e.printStackTrace();
            }
        });
        System.out.println("Batch job executed successfully");
        return new ResponseEntity<>(Map.of("response", "success"), HttpStatus.ACCEPTED);
    }
}
