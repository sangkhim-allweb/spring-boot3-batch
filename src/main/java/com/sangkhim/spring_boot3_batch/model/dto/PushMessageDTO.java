package com.sangkhim.spring_boot3_batch.model.dto;

import java.util.List;
import lombok.Data;

@Data
public class PushMessageDTO {

  private String category;
  private String title;
  private String body;
  private int badge;
  private String sound;
  private String type;
  private String id;
  private String image;
  private List<PushNotificationTokenDTO> pushNotificationTokenList;

  public String getSound() {
    if (sound == null) {
      sound = "default";
    }
    return sound;
  }

  public String getType() {
    if (type == null) {
      type = "NOTIFICATION";
    }
    return type;
  }
}
