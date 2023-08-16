package com.sangkhim.spring_boot3_batch.config.batch.job2;

import com.sangkhim.spring_boot3_batch.model.dto.CoffeeDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.ClassPathResource;

public class Reader implements ItemReader<FlatFileItemReader> {

  private String fileInput;

  @Override
  public FlatFileItemReader read() {
    return new FlatFileItemReaderBuilder()
        .name("coffeeItemReader")
        .resource(new ClassPathResource(fileInput))
        .delimited()
        .names(new String[] {"brand", "origin", "characteristics"})
        .fieldSetMapper(
            new BeanWrapperFieldSetMapper() {
              {
                setTargetType(CoffeeDTO.class);
              }
            })
        .build();
  }

  public FlatFileItemReader<CoffeeDTO> setFileInput(
      String fileInput, String testParam1, String testParam2) {
    this.fileInput = fileInput;
    return this.read();
  }
}
