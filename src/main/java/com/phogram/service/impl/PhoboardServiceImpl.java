package com.phogram.service.impl;

import com.phogram.dto.PhoboardDTO;
import com.phogram.dto.SearchDTO;
import com.phogram.repository.PhoboardRepository;
import com.phogram.service.PhoboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Service
public class PhoboardServiceImpl implements PhoboardService {

    private static final Logger log = LoggerFactory.getLogger(PhoboardServiceImpl.class);

    @Autowired
    private PhoboardRepository phoboardRepository;


    @Override
    public boolean add(PhoboardDTO phoboardDTO) {
        return false;
    }

    @Override
    public Optional<PhoboardDTO> findById(Long id) {
        return null;
    }

    @Override
    public Page<PhoboardDTO> findByTitle(SearchDTO searchDTO) {
        return null;
    }

    @Override
    public Page<PhoboardDTO> findAll(SearchDTO searchDTO) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
