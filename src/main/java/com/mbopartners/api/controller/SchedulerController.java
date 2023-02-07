package com.mbopartners.api.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SchedulerController {
    @Autowired
    JobLauncher jobLauncher;

    //@Autowired
    //Job job;

    @Autowired
    List<Job> jobs;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Scheduled(fixedDelay = 60000, initialDelay = 5000)
    public void scheduleByFixedRate() throws Exception {
        System.out.println("Batch job starting");
        Map<String, Long> map = new LinkedHashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now().plusDays(3);
        for (Job job : jobs) {
            try {
                JobExecution jobExecution = null;
                if (job.getName().equals("processJob")) {
                    JobParameters jobParameters = new JobParametersBuilder()
                            .addString("date", localDateTime.toString())
                            .toJobParameters();
                    jobExecution = jobLauncher.run(job, jobParameters);
                    map.put(job.getName(), jobExecution.getJobId());
                } else if (job.getName().equals("runningTest")) {
                    JobParameters jobParameters = new JobParametersBuilder()
                            .addString("previousJobId", map.get("processJob").toString())
                            .addString("date", localDateTime.toString())
                            .toJobParameters();
                    jobExecution = jobLauncher.run(job, jobParameters);
                } else {

                }
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
