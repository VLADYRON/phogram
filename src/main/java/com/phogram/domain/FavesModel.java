package com.phogram.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gavin on 2017. 5. 5..
 * include Like or unlike
 */
@Data
@Entity
@Table(name = "Faves")
public class FavesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 1)
    private int type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @ManyToOne
    @JsonBackReference
    private UserModel user;

    @ManyToOne
    @JoinColumn(name="phoboard_id")
    private PhoboardModel phoboards;
}
