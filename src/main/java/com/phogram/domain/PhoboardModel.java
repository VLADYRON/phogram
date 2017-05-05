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
@Table(name = "phoboard")
public class PhoboardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descr;
    private String name;
    private String path;
    private int status;
    @CreationTimestamp
    private Date createAt;
    @CreationTimestamp
    private Date updateAt;
    @CreationTimestamp
    private Date deleteAt;

}
