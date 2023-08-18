package com.sangkhim.spring_boot3_batch.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan("com.sangkhim.spring_boot3_batch.schedule")
public class SpringSchedulingConfig {}
