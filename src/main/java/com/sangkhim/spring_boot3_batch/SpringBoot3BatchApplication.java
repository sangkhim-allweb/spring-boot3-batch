package com.sangkhim.spring_boot3_batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class SpringBoot3BatchApplication implements CommandLineRunner {

  private final JobLauncher jobLauncher;
  private final Job job;

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

    log.info("BATCH STATUS :: START");
    JobExecution execution = jobLauncher.run(job, jobParameters);
    log.info("BATCH STATUS :: " + execution.getStatus());
  }
}
