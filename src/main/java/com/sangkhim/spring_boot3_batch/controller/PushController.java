package com.sangkhim.spring_boot3_batch.controller;

import com.sangkhim.spring_boot3_batch.config.event.PushNotificationEvent;
import com.sangkhim.spring_boot3_batch.model.dto.PushMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push-server")
@RequiredArgsConstructor
public class PushController {

  private final ApplicationEventPublisher applicationEventPublisher;

  @PostMapping("/call-event")
  public ResponseEntity<String> callEvent() {
    PushMessageDTO pushMessageDTO = new PushMessageDTO();
    pushMessageDTO.setBody("Hello World");
    PushNotificationEvent pushNotificationEvent = new PushNotificationEvent(this, pushMessageDTO);
    applicationEventPublisher.publishEvent(pushNotificationEvent);

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @PostMapping("/send")
  public ResponseEntity<String> send(@RequestBody PushMessageDTO pushMessageDTO) {
    try {
      Thread.sleep(5000);
      System.out.println(pushMessageDTO);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    return new ResponseEntity<>("OK", HttpStatus.OK);
  }
}
