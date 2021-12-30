package com.gmail.creativegeeksuresh.zyncky.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  
  private String uid;

  private String username;

  private String password;
}
