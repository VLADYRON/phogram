package com.phogram.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Data
@Entity
@Table(name = "tags")
public class TagModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tag;
    private int count;
    private Date createAt;
}
