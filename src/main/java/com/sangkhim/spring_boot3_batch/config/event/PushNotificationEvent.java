package com.sangkhim.spring_boot3_batch.config.event;

import com.sangkhim.spring_boot3_batch.model.dto.PushMessageDTO;
import org.springframework.context.ApplicationEvent;

public class PushNotificationEvent extends ApplicationEvent {

  private PushMessageDTO pushMessageDTO;

  public PushNotificationEvent(Object source, PushMessageDTO pushMessageDTO) {
    super(source);
    this.pushMessageDTO = pushMessageDTO;
  }

  public PushMessageDTO getPushMessage() {
    return pushMessageDTO;
  }
}
