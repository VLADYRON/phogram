package com.phogram.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Data
@Entity
@Table(name = "follows")
public class FollowModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int status;
    private int type;
    private Date createAt;
    private Date updateAt;

    @ManyToOne
    @JsonBackReference
    private UserModel user;
}
