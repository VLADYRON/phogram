package com.phogram.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "phoboard_tags",
            joinColumns = @JoinColumn(name = "phoboard_id"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private List<PhoboardModel> phoboard;
}
