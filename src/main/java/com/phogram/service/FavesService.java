package com.phogram.service;

import com.phogram.dto.FavesDTO;
import com.phogram.dto.PhoboardDTO;
import com.phogram.dto.SearchDTO;

import java.util.List;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface FavesService {

    boolean add(FavesDTO favesDTO);
    boolean deleteById(Long PhoboardId);
    List<PhoboardDTO> findAll(SearchDTO searchDTO);
}
