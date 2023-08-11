package com.sangkhim.spring_boot3_batch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CoffeeDTO {

  private String brand;
  private String origin;
  private String characteristics;
}
