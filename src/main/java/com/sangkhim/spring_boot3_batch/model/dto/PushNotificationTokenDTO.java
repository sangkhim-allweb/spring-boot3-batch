package com.sangkhim.spring_boot3_batch.model.dto;

import lombok.Data;

@Data
public class PushNotificationTokenDTO {

  private Long pushNotificationTokenId;
  private String username;
  private DeviceType deviceType;
  private String token;

  public enum DeviceType {
    ANDROID,
    IOS,
    WEB
  }
}
