package com.gmail.creativegeeksuresh.zyncky.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Column
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Integer sno;

    @Column(unique = true, nullable = false)
    private String uid;

    /**
     * username may be userid or email
     */
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean status = Boolean.TRUE;

    @Column(name = "created_at",nullable = false)
    private Date createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;

}
