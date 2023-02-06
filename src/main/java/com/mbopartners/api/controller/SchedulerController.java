package com.mbopartners.api.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Configuration
public class SchedulerController {
    @Autowired
    JobLauncher jobLauncher;

    //@Autowired
    //Job job;

    @Autowired
    List<Job> jobs;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

    @Scheduled(fixedDelay = 60000, initialDelay = 5000)
    public void scheduleByFixedRate() throws Exception {
        System.out.println("Batch job starting");
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("time", format.format(Calendar.getInstance().getTime())).toJobParameters();
        //jobLauncher.run(job, jobParameters);
        //jobs.forEach(job -> {
        for (Job job : jobs) {
            System.out.println(job.getName());
            try {
                JobExecution jobExecution = jobLauncher.run(job, jobParameters);
                System.out.println(jobExecution.getStatus());
                if (jobExecution.getStatus() == BatchStatus.FAILED) {

                    break;
                }
            } catch (Exception e) {
                //logger.error("Job {} cannot be executed", job.getName());
                e.printStackTrace();
            }
        }
        //});
        System.out.println("Batch job executed successfully");
    }

}
