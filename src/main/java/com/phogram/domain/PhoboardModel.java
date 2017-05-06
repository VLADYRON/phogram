package com.phogram.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JsonBackReference
    private UserModel user;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "phoboard")
    private List<TagModel> tags;
}
