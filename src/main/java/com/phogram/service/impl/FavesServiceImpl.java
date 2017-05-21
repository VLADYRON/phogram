package com.phogram.service.impl;

import com.phogram.dto.FavesDTO;
import com.phogram.dto.PhoboardDTO;
import com.phogram.dto.SearchDTO;
import com.phogram.repository.FavesRepository;
import com.phogram.service.FavesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Service
public class FavesServiceImpl implements FavesService{

    private static final Logger log = LoggerFactory.getLogger(FavesServiceImpl.class);

    @Autowired
    private FavesRepository favesRepository;

    @Override
    public boolean add(FavesDTO favesDTO) {
        return false;
    }

    @Override
    public boolean deleteById(Long PhoboardId) {
        return false;
    }

    @Override
    public List<PhoboardDTO> findAll(SearchDTO searchDTO) {
        return null;
    }
}
