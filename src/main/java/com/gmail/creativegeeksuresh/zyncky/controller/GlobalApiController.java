package com.gmail.creativegeeksuresh.zyncky.controller;

import java.util.Map;

import com.gmail.creativegeeksuresh.zyncky.dto.CustomErrorResponse;
import com.gmail.creativegeeksuresh.zyncky.dto.UserDto;
import com.gmail.creativegeeksuresh.zyncky.exception.UserAlreadyExistsException;
import com.gmail.creativegeeksuresh.zyncky.service.internal.UserService;
import com.gmail.creativegeeksuresh.zyncky.service.util.CustomJwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/global")
public class GlobalApiController {

    @Autowired
    UserService userService;
  
    @Autowired
    CustomJwtService customJwtService;

    @PostMapping(value = "/create-account")
    public ResponseEntity<?> createUserAccount(@RequestBody UserDto request) {
      try {
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
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

    @PostMapping(value = "/reset-password")
    public ResponseEntity<?> resetUserPassword(@RequestBody Map<String,Object> request) {
        try {
            if (request != null && request.containsKey("key")) {
                return new ResponseEntity<>(Map.of(), HttpStatus.OK);
            } else {
                throw new Exception("Unable to process request due to invalid data");
            }
        } catch (UserAlreadyExistsException uaex) {
            System.err.println(uaex.getLocalizedMessage());
            return new ResponseEntity<>(
                    new CustomErrorResponse(HttpStatus.CONFLICT.value(), uaex.getLocalizedMessage(), ""),
                    HttpStatus.CONFLICT);
        } catch (Exception ex) {
            System.err.println(ex.getLocalizedMessage());
            return new ResponseEntity<>(
                    new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage(), ""),
                    HttpStatus.BAD_REQUEST);
        }
    }
    
}
