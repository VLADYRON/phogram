package com.phogram.service.impl;

import com.phogram.domain.UserModel;
import com.phogram.dto.UserDTO;
import com.phogram.repository.UserRepository;
import com.phogram.service.UserService;
import com.phogram.utils.PGConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Service
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean create(UserDTO userDTO){
        if(!ObjectUtils.isEmpty(userRepository.save(userDTO.toUserModel()))){
            return true;
        }
        return false;
    }

    @Override
    public boolean update(UserDTO userDTO){
        try {
            UserModel currentUser = userRepository.findByUsername(userDTO.getUsername());
            if(currentUser != null){
                UserModel updateUser = userDTO.toUserModel();
                currentUser.setLastName(updateUser.getLastName());
                currentUser.setFirstName(updateUser.getFirstName());
                currentUser.setGravataUrl(updateUser.getGravataUrl());
                currentUser.setUpdateAt(new Date());
                currentUser.setPassword(updateUser.getPassword());
                currentUser.setPhone(updateUser.getPhone());
                currentUser.setUsername(updateUser.getUsername());
                currentUser.setEmail(updateUser.getEmail());

                userRepository.save(currentUser);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(String username) {
        try {
            //userRepository.deleteByUsername(username);
            UserModel deleteUser =userRepository.findByUsername(username);
            deleteUser.setDeleteAt(new Date());
            deleteUser.setActive(PGConstants.USER_DISABLE);
            userRepository.saveAndFlush(deleteUser);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return false;
    }

    @Override
    public Optional<UserDTO> findByUsername(String username){
        try {
            UserModel userModel = userRepository.findByUsername(username);
            if(ObjectUtils.isEmpty(userModel)) return Optional.empty();
            UserDTO userDTO = new UserDTO(
                    userModel.getFirstName(),
                    userModel.getLastName(),
                    userModel.getUsername(),
                    userModel.getPassword(),
                    userModel.getEmail(),
                    userModel.getPhone(),
                    userModel.getGravataUrl());
            return Optional.of(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return Optional.empty();
        }
    }
}
