package com.phogram.service.impl;

import com.phogram.dto.SearchDTO;
import com.phogram.dto.TagDTO;
import com.phogram.repository.TagRepository;
import com.phogram.service.KeywordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by gavin on 2017. 5. 5..
 */
@Service
public class KeywordServiceImpl implements KeywordService {

    private static final Logger log = LoggerFactory.getLogger(KeywordServiceImpl.class);

    @Autowired
    private TagRepository tagRepository;

    @Override
    public boolean add(TagDTO tagDTO) {
        return false;
    }

    @Override
    public List<TagDTO> findByTag(SearchDTO searchDTO) {
        return null;
    }

    @Override
    public Optional<TagDTO> findById(Long id) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
