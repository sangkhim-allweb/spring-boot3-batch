package com.sangkhim.spring_boot3_batch.scheduling;

import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:scheduled.properties")
@RequiredArgsConstructor
@Slf4j
public class ScheduledAnnotationExample {

  private final JobLauncher jobLauncher;
  private final Job job;

  @Async
  @Scheduled(cron = "${cron.expression}")
  public void scheduleTaskUsingExternalizedCronExpression()
      throws JobInstanceAlreadyCompleteException,
          JobExecutionAlreadyRunningException,
          JobParametersInvalidException,
          JobRestartException {

    JobParameters jobParameters =
        new JobParametersBuilder()
            .addDate("date", new Date())
            .addString("testParam1", "testParam1")
            .addString("testParam2", "testParam2")
            .toJobParameters();

    log.info("BATCH STATUS :: START");
    JobExecution execution = jobLauncher.run(job, jobParameters);
    log.info("BATCH STATUS :: " + execution.getStatus());
  }
}
