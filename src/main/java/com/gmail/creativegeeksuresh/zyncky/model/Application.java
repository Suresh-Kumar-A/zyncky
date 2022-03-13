package com.gmail.creativegeeksuresh.zyncky.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gmail.creativegeeksuresh.zyncky.constants.MfaType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "applications")
public class Application {

    @Column
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer sno;

    @Column(name = "app_id", unique = true, nullable = false)
    private String appId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String description = "";

    @Column(name = "app_prefix", unique = true, nullable = false)
    private String appPrefix;

    @Column(nullable = false)
    private Boolean status = Boolean.TRUE;

    @Column(name = "created_at", nullable = false)
    private Date createAt;

    @Column(name = "app_level_mfa", nullable = false)
    private Boolean appLevelMfa = Boolean.TRUE;

    @Enumerated(EnumType.ORDINAL)
    private MfaType mfaType = MfaType.NONE;
 
}
