package com.sangkhim.spring_boot3_batch.config.batch.job1;

import com.sangkhim.spring_boot3_batch.config.batch.JobCompletionNotificationListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Job1Configuration {

  @Bean
  public Job job1(
      JobRepository jobRepository,
      JobCompletionNotificationListener listener,
      PlatformTransactionManager transactionManager) {

    return new JobBuilder("job1", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(job1Step1(jobRepository, transactionManager))
        .end()
        .build();
  }

  @Bean
  public Step job1Step1(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {

    return new StepBuilder("job1Step1", jobRepository)
        .<String, String>chunk(1, transactionManager)
        .reader(new Reader())
        .processor(new Processor())
        .writer(new Writer())
        .build();
  }
}
