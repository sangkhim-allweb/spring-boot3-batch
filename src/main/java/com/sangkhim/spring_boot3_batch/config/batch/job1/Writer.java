package com.sangkhim.spring_boot3_batch.config.batch.job1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class Writer implements ItemWriter<String> {

  @Override
  public void write(Chunk<? extends String> chunk) throws Exception {
    for (String msg : chunk) {
      log.info("Writing the data :: " + msg);
    }
  }
}
