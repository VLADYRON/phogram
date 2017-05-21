package com.phogram.service;

import com.phogram.dto.PhoboardDTO;
import com.phogram.dto.SearchDTO;
import com.phogram.dto.TagDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface KeywordService {
    boolean add(TagDTO tagDTO);
    List<TagDTO> findByTag(SearchDTO searchDTO);
    Optional<TagDTO> findById(Long id);
    boolean deleteById(Long id);
}
