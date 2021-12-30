package com.gmail.creativegeeksuresh.zyncky.service;

import java.util.Date;

import com.gmail.creativegeeksuresh.zyncky.dto.ApplicationDto;
import com.gmail.creativegeeksuresh.zyncky.model.Application;
import com.gmail.creativegeeksuresh.zyncky.repository.AppRepository;
import com.gmail.creativegeeksuresh.zyncky.service.util.CustomUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    
    @Autowired
    AppRepository appRepository;

    @Autowired
    CustomUtils customUtils;

    public Application createApplication(ApplicationDto app) throws Exception{

        if (appRepository.findByName(app.getName()) != null)
            throw new Exception("App with simlar data exists");

        Application newApp = new Application();
        BeanUtils.copyProperties(app, newApp);
        newApp.setAppId(customUtils.generateToken());
        newApp.setCreateAt(new Date());
        newApp.setAppPrefix(app.getName().substring(0, 3).toUpperCase());
        
        return appRepository.save(newApp);
    }
}
