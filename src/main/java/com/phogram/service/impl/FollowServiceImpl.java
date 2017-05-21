package com.phogram.service.impl;

import com.phogram.repository.FollowRepository;
import com.phogram.service.FollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Service
public class FollowServiceImpl implements FollowService{

    private static final Logger log = LoggerFactory.getLogger(FollowServiceImpl.class);

    @Autowired
    private FollowRepository followRepository;

    @Override
    public boolean follow(Long userIndex) {
        return false;
    }

    @Override
    public boolean unFollow(Long userIndex) {
        return false;
    }
}
