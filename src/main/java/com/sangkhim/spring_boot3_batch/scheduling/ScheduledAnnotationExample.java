package com.sangkhim.spring_boot3_batch.scheduling;

import java.util.Date;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:scheduled.properties")
@Slf4j
public class ScheduledAnnotationExample {

  private final JobLauncher jobLauncher;
  private final Job job1;
  private final Job job2;

  public ScheduledAnnotationExample(
      JobLauncher jobLauncher, @Qualifier("job1") Job job1, @Qualifier("job2") Job job2) {
    this.jobLauncher = jobLauncher;
    this.job1 = job1;
    this.job2 = job2;
  }

  @Async
  @Scheduled(cron = "${cron.expression}")
  public void scheduleTaskUsingExternalizedCronExpression() {

    JobParameters jobParameters =
        new JobParametersBuilder()
            .addDate("date", new Date())
            .addString("testParam1", "testParam1")
            .addString("testParam2", "testParam2")
            .toJobParameters();

    try {
      log.info("JOB 1 STARTED");
      JobExecution execution = jobLauncher.run(job1, jobParameters);
      log.info("JOB 1 STATUS :: " + execution.getStatus());

      log.info("JOB 2 STARTED");
      execution = jobLauncher.run(job2, jobParameters);
      log.info("JOB 2 STATUS :: " + execution.getStatus());
    } catch (JobExecutionAlreadyRunningException e) {
      throw new RuntimeException(e);
    } catch (JobRestartException e) {
      throw new RuntimeException(e);
    } catch (JobInstanceAlreadyCompleteException e) {
      throw new RuntimeException(e);
    } catch (JobParametersInvalidException e) {
      throw new RuntimeException(e);
    }
  }
}
