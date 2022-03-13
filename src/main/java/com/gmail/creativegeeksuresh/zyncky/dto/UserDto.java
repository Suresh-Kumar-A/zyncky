package com.gmail.creativegeeksuresh.zyncky.dto;

import java.util.Date;
import java.util.List;

import com.gmail.creativegeeksuresh.zyncky.model.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private String username;

  private String password;

  private Integer sno;

  private String uid;

  private Boolean status = Boolean.TRUE;

  private Date createdAt;

  private List<Role> roles;
}
