package com.gmail.creativegeeksuresh.zyncky.service;

import java.util.Date;
import java.util.List;

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

    public Application createApplication(ApplicationDto app) throws Exception {

        if (appRepository.findByName(app.getName()) != null)
            throw new Exception("App with simlar data exists");

        Application newApp = new Application();
        BeanUtils.copyProperties(app, newApp);
        newApp.setAppId(customUtils.generateToken());
        newApp.setCreateAt(new Date());
        if (newApp.getAppPrefix() == null || newApp.getAppPrefix().isBlank())
            newApp.setAppPrefix(app.getName().substring(0, 3).toUpperCase());

        return appRepository.save(newApp);
    }

    public Application findByAppId(String appId) throws Exception{
        return appRepository.findByAppId(appId);
    }

    public Application findByAppName(String appName) throws Exception{
        return appRepository.findByName(appName);
    }

    public List<Application> getAllApps() throws Exception{
        return (List<Application>) appRepository.findAll();
    }

    public void deleteByAppId(String appId) throws Exception {
        Application tmpApp = findByAppId(appId);
        if (tmpApp != null)
            appRepository.delete(tmpApp);
        else
            throw new Exception("App Does not exists");
    }

    public Application updateApplication(ApplicationDto app) throws Exception {
        Application tmpApp = findByAppId(app.getAppId());
        if (tmpApp != null) {
            if (tmpApp.getName() != null && tmpApp.getName().trim() != "") {
                tmpApp.setName(app.getName());
            }
            if (tmpApp.getDescription() != null && tmpApp.getDescription().trim() != "") {
                tmpApp.setDescription(app.getDescription());
            }
            if (tmpApp.getMfaType() != null) {
                tmpApp.setMfaType(tmpApp.getMfaType());
            }
            if (tmpApp.getAppLevelMfa() != null) {
                tmpApp.setAppLevelMfa(tmpApp.getAppLevelMfa());
            }

            return appRepository.save(tmpApp);
        } else
            throw new Exception("User Does not Exists");
    }
}
