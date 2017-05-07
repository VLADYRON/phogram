package com.phogram.service;

import com.phogram.dto.UserDTO;

import java.util.Optional;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface UserService {
    boolean create(UserDTO userDTO) throws Exception;
    boolean update(UserDTO userDTO) throws Exception;
    boolean delete(String username) throws Exception;
    Optional<UserDTO> findByUsername(String username) throws Exception;

}
