package com.phogram.repository;

import com.phogram.domain.PhoboardModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface PhoboardRepository extends JpaRepository<PhoboardModel,Long> {
}
