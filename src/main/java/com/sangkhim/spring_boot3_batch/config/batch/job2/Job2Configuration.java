package com.sangkhim.spring_boot3_batch.config.batch.job2;

import com.sangkhim.spring_boot3_batch.config.batch.JobCompletionNotificationListener;
import com.sangkhim.spring_boot3_batch.model.dto.CoffeeDTO;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class Job2Configuration {

  @Value("${file.input}")
  private String fileInput;

  @Bean
  public Job job2(
      JobRepository jobRepository,
      JobCompletionNotificationListener listener,
      Step job2Step1,
      @Value("${jobName:testParam1}") String testParam1,
      @Value("${jobName:testParam2}") String testParam2) {

    return new JobBuilder("job2", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(job2Step1)
        .end()
        .build();
  }

  @Bean
  public Step job2Step1(
      JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      JdbcBatchItemWriter writer,
      @Value("${jobName:testParam1}") String testParam1,
      @Value("${jobName:testParam2}") String testParam2) {

    return new StepBuilder("job2Step1", jobRepository)
        .<CoffeeDTO, CoffeeDTO>chunk(10, transactionManager)
        .reader(new Reader().setFileInput(fileInput, testParam1, testParam2))
        .processor(new Processor())
        .writer(writer)
        .build();
  }

  //  @Bean
  //  public FlatFileItemReader reader(String testParam1, String testParam2) {
  //
  //    return new FlatFileItemReaderBuilder()
  //        .name("coffeeItemReader")
  //        .resource(new ClassPathResource(fileInput))
  //        .delimited()
  //        .names(new String[] {"brand", "origin", "characteristics"})
  //        .fieldSetMapper(
  //            new BeanWrapperFieldSetMapper() {
  //              {
  //                setTargetType(CoffeeDTO.class);
  //              }
  //            })
  //        .build();
  //  }

  //  @Bean
  //  public CoffeeItemProcessor processor(String testParam1, String testParam2) {
  //
  //    return new CoffeeItemProcessor();
  //  }

  @Bean
  public JdbcBatchItemWriter writer(DataSource dataSource) {

    return new JdbcBatchItemWriterBuilder()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql(
            "INSERT INTO coffee (brand, origin, characteristics) VALUES (:brand, :origin, :characteristics)")
        .dataSource(dataSource)
        .build();
  }
}
