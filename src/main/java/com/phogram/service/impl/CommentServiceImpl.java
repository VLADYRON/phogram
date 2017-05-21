package com.phogram.service.impl;

import com.phogram.dto.CommentDTO;
import com.phogram.repository.CommentRepository;
import com.phogram.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean add(CommentDTO commentDTO) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public List<CommentDTO> findByPhoboardId(Long phoboardId) {
        return null;
    }
}
