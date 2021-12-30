package com.gmail.creativegeeksuresh.zyncky.dto;

import java.util.Date;

import com.gmail.creativegeeksuresh.zyncky.service.util.AppConstants.MfaType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {

    private Integer sno;

    private String appId;

    private String name;

    private String description = "";

    private String appPrefix;

    private Boolean status = Boolean.TRUE;

    private Boolean appLevelMfa = Boolean.TRUE;

    private Date createAt;

    private MfaType mfaType = MfaType.GOOGLE_AUTH;

}
