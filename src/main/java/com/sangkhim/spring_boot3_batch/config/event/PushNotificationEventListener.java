package com.sangkhim.spring_boot3_batch.config.event;

import com.sangkhim.spring_boot3_batch.controller.PushController;
import com.sangkhim.spring_boot3_batch.model.dto.PushMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PushNotificationEventListener implements ApplicationListener<PushNotificationEvent> {

  private final PushController pushController;

  @Override
  public void onApplicationEvent(PushNotificationEvent event) {
    PushMessageDTO pushMessageDTO = event.getPushMessage();
    pushController.send(pushMessageDTO);
  }
}
