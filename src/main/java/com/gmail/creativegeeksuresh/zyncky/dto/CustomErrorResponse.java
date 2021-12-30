package com.gmail.creativegeeksuresh.zyncky.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
  private int errorCode;
  private String errorMessage;
  private String errorDescription;
}
