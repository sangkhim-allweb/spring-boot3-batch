package com.sangkhim.spring_boot3_batch.config.batch.job1;

import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<String, String> {

  @Override
  public String process(String data) {
    return data.toUpperCase();
  }
}
