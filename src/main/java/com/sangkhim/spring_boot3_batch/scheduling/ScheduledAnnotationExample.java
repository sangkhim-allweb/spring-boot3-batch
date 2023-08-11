package com.sangkhim.spring_boot3_batch.scheduling;

import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:scheduled.properties")
public class ScheduledAnnotationExample {

  @Async
  @Scheduled(cron = "${cron.expression}")
  public void scheduleTaskUsingExternalizedCronExpression() {
    System.out.println(
        "schedule tasks using externalized cron expressions - "
            + System.currentTimeMillis() / 1000);
  }
}
