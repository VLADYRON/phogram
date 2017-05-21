package com.phogram.service;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface FollowService {

    boolean follow(Long userIndex);
    boolean unFollow(Long userIndex);

}
