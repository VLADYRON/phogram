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
@Table(name = "comment")
public class CommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private Date createAt;

    @ManyToOne
    @JsonBackReference
    private UserModel user;

    @ManyToOne
    @JoinColumn(name="phoboard_id")
    private PhoboardModel phoboards;
}
