package com.phogram.service;

import com.phogram.dto.CommentDTO;
import com.phogram.dto.SearchDTO;

import java.util.List;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface CommentService {

    boolean add(CommentDTO commentDTO);
    boolean deleteById(Long id);
    List<CommentDTO> findByPhoboardId(Long phoboardId);
}
