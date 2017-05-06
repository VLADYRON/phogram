package com.phogram.service.impl;

import com.phogram.dto.UserDTO;
import com.phogram.repository.UserRepository;
import com.phogram.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean create(UserDTO userDTO) throws Exception {
        if(!ObjectUtils.isEmpty(userRepository.save(userDTO.toUserModel()))){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(UserDTO userDTO) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Long index) throws Exception {
        return false;
    }

    @Override
    public UserDTO findByUsername(String userName) throws Exception {
        return null;
    }

    @Override
    public UserDTO findByEmail(String email) throws Exception {
        return null;
    }

    @Override
    public boolean duplicateCheck(String check) throws Exception {
        if(!ObjectUtils.isEmpty(userRepository.findByEmailOrUsernameOrPhone(check,check,check))){
            return true;
        }
        return false;
    }
}
