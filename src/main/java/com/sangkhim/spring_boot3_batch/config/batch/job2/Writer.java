package com.sangkhim.spring_boot3_batch.config.batch.job2;

import com.sangkhim.spring_boot3_batch.model.dto.CoffeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

@Slf4j
public class Writer implements ItemWriter<CoffeeDTO> {

  @Override
  public void write(Chunk<? extends CoffeeDTO> chunk) {
    log.info(chunk.toString());
  }
}
