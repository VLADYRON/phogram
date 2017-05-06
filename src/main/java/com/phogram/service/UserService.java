package com.phogram.service;

import com.phogram.dto.UserDTO;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface UserService {
    boolean create(UserDTO userDTO) throws Exception;
    boolean update(UserDTO userDTO) throws Exception;
    boolean delete(Long index) throws Exception;
    UserDTO findByUsername(String userName) throws Exception;
    UserDTO findByEmail(String email) throws Exception;
    /*email,phone,username*/
    boolean duplicateCheck(String check) throws Exception;
}
