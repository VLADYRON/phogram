package com.phogram.repository;

import com.phogram.domain.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface CommentRepository extends JpaRepository<CommentModel,Long> {
}
