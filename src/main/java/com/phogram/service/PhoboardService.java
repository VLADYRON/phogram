package com.phogram.service;

import com.phogram.dto.PhoboardDTO;
import com.phogram.dto.SearchDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Created by gavin on 2017. 5. 5..
 */
public interface PhoboardService {

    /**
     * 사진 저장시 태그가 없으면 기본 값으로 이름을 태그로 저장하고 해당 태그의 카운트를 0 으로 설정
     * @param phoboardDTO
     * @return
     */
    boolean add(PhoboardDTO phoboardDTO);

    Optional<PhoboardDTO> findById(Long id);

    /**
     *
     * 검색시 이름 혹은 태그로 검색
     * 이름 매칭을 우선순위로 하고 , 결과가 없을 경우 태그 매칭
     * 업로드 시간 순 , desc 정렬
     * @param searchDTO
     * @return
     */
    Page<PhoboardDTO> findByTitle(SearchDTO searchDTO);
    Page<PhoboardDTO> findAll(SearchDTO searchDTO);
    boolean deleteById(Long id);

}
