package com.phogram.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Column(name="username", nullable = false, updatable = false,unique = true,length = 50)
    private String username;
    @Column(name="first_name", nullable = false,length = 50)
    private String firstName;
    @Column(name="last_name", nullable = false,length = 50)
    private String lastName;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="email", nullable = false, updatable = false,unique = true,length = 100)
    private String email;
    @Column(name="phone", updatable = false,unique = true,length = 13)
    private String phone;
    private String gravataUrl;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @Column(length = 1)
    private int enable;
    @Column(length = 1)
    private int status;

    //NOTE: mappedBy=user must be field name of PhoboardModel (private UserModel user)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PhoboardModel> phoboards;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<FollowModel> follows;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CommentModel> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<FavesModel> faves;
}
