package com.gmail.creativegeeksuresh.zyncky.controller;

import com.gmail.creativegeeksuresh.zyncky.dto.ApplicationDto;
import com.gmail.creativegeeksuresh.zyncky.dto.CustomErrorResponse;
import com.gmail.creativegeeksuresh.zyncky.model.Application;
import com.gmail.creativegeeksuresh.zyncky.service.internal.AppService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    } catch (Exception ex) {
      System.err.println(ex.getLocalizedMessage());
      return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), ""),
          HttpStatus.BAD_REQUEST);
    }
  }

  @PatchMapping(value = "/update")
  public ResponseEntity<?> updateApplication(@RequestBody ApplicationDto app) {
    try {
      return new ResponseEntity<>(appService.updateApplication(app), HttpStatus.OK);
    }catch (Exception ex) {
      System.err.println(ex.getLocalizedMessage());
      return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), ""),
          HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping(value = "/delete")
  public ResponseEntity<?> deleteAppByAppId(@RequestParam(value = "appid") String appId) {
    try {
      appService.deleteByAppId(appId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception ex) {
      System.err.println(ex.getLocalizedMessage());
      return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), ""),
          HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/view-all")
  public ResponseEntity<?> getAllApps() {
    try {
      return new ResponseEntity<>(appService.getAllApps(), HttpStatus.OK);
    }catch (Exception ex) {
      System.err.println(ex.getLocalizedMessage());
      return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), ""),
          HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping(value = "/view")
  public ResponseEntity<?> getUserByUid(@RequestParam(value = "appid") String appId) {
    try {
      Application app = appService.findByAppId(appId);
      if (app != null)
        return new ResponseEntity<>(app, HttpStatus.OK);
      else
        throw new Exception("App does not exists");
    } catch (Exception ex) {
      System.err.println(ex.getLocalizedMessage());
      return new ResponseEntity<>(new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), ""),
          HttpStatus.BAD_REQUEST);
    }
  }
}
