package com.phogram.repository;

import com.phogram.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface UserRepository extends JpaRepository<UserModel,Long> {
    UserModel findByUsername(String username);
    void deleteByUsername(String username);
}
