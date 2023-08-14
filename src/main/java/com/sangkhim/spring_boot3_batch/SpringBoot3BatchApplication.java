package com.sangkhim.spring_boot3_batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBoot3BatchApplication implements CommandLineRunner {

  @Autowired private JobLauncher jobLauncher;

  @Autowired private Job job;

  public static void main(String[] args) {
    SpringApplication.run(SpringBoot3BatchApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    JobParameters jobParameters =
        new JobParametersBuilder()
            .addString("testParam1", "testParam1")
            .addString("testParam2", "testParam2")
            .toJobParameters();

    System.out.println("BATCH STATUS :: START");
    JobExecution execution = jobLauncher.run(job, jobParameters);
    System.out.println("BATCH STATUS :: " + execution.getStatus());
  }
}
