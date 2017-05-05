package com.phogram.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Data
@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="username", nullable = false, updatable = false,unique = true)
    private String username;
    @Column(name="first_name", nullable = false)
    private String firstName;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="email", nullable = false, updatable = false,unique = true)
    private String email;
    @Column(name="phone", updatable = false,unique = true)
    private String phone;
    private String gravataUrl;
    @CreationTimestamp
    private Date createAt;
    @CreationTimestamp
    private Date updateAt;
    private int enable;
    private int status;
}
