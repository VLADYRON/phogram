package com.phogram.repository;

import com.phogram.domain.FavesModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface FavesRepository extends JpaRepository<FavesModel,Long>{
}
