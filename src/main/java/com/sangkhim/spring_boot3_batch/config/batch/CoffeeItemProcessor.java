package com.sangkhim.spring_boot3_batch.config.batch;

import com.sangkhim.spring_boot3_batch.model.dto.CoffeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class CoffeeItemProcessor implements ItemProcessor<CoffeeDTO, CoffeeDTO> {

  @Override
  public CoffeeDTO process(final CoffeeDTO coffee) {
    String brand = coffee.getBrand().toUpperCase();
    String origin = coffee.getOrigin().toUpperCase();
    String characteristics = coffee.getCharacteristics().toUpperCase();

    CoffeeDTO transformedCoffee = new CoffeeDTO(brand, origin, characteristics);
    log.info("Converting ( {} ) into ( {} )", coffee, transformedCoffee);

    return transformedCoffee;
  }
}
