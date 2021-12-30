package com.gmail.creativegeeksuresh.zyncky.controller;

import com.gmail.creativegeeksuresh.zyncky.dto.ApplicationDto;
import com.gmail.creativegeeksuresh.zyncky.dto.CustomErrorResponse;
import com.gmail.creativegeeksuresh.zyncky.exception.UserAlreadyExistsException;
import com.gmail.creativegeeksuresh.zyncky.service.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/app")
public class AppApiController {

  @Autowired
  AppService appService;

  @PostMapping(value = "/create")
  public ResponseEntity<?> createUserAccount(@RequestBody ApplicationDto request) {
    try {
      return new ResponseEntity<>(appService.createApplication(request), HttpStatus.CREATED);
    } catch (UserAlreadyExistsException uaex) {
      System.err.println(uaex.getLocalizedMessage());
      return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.CONFLICT.value(), uaex.getLocalizedMessage(), ""),
          HttpStatus.CONFLICT);
    } catch (Exception ex) {
      System.err.println(ex.getLocalizedMessage());
      return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), ""),
          HttpStatus.BAD_REQUEST);
    }
  }
}
