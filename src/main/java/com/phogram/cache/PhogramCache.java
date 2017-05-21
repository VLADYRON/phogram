package com.phogram.cache;

/**
 * Created by gavin on 2017. 5. 5..
 * - 캐시할 항목
 * 1. 사용자가 검색한 키워드 : 검색한 키워드 결과가 1개 이상 존재 할 경우 해당 키워드에 대해 카운트한다. 키워드 => 카운트
 * 2. 사용자의 팔로워에 대해 카운트 : 사용자 아이디+팔로워 => 카운트
 * 3. 사진에 대한 faves (like) 카운트 : phoboard+faves => 카운트
 *
 * - 레디스 사용시 저장 방식은 파일로 하도록 한다
 */
public interface PhogramCache {

}
