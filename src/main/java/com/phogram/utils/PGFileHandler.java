package com.phogram.utils;


import java.io.File;

import com.phogram.config.StorageException;
import com.phogram.config.StorageFileNotFoundException;
import com.phogram.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by gavin on 2017. 4. 1..
 */
@Component
public class PGFileHandler {
    private static final Logger log = LoggerFactory.getLogger(PGFileHandler.class);

    @Value("#{'${phogram.allow.file.define}'.split(',')}")
    private List<String> checkFileList; // 허가된 파일 목록

    public Path getRootDirPath(String location){
        try {
            if(Files.notExists(Paths.get(location))){
                File file = new File(location);
                file.mkdirs();
                return file.toPath();
            }else{
                return Paths.get(location);
            }
        } catch (Exception e) {
            log.error("getRootDirPath: "+e);
            throw new StorageException("Failed create dir");
        }
    }

    /*파일 읽기*/
    public Resource loadAsResource(String locationFilename) {
        try {
            Path file = load(locationFilename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
                log.error("Could not read file: " + locationFilename);
                throw new StorageFileNotFoundException("Could not read file: " + locationFilename);
            }
        } catch (MalformedURLException e) {
            log.error("loadAsResource: "+e);
            throw new StorageFileNotFoundException("Could not read file: " + locationFilename, e);
        }
    }
    /*파일 경로 가져오기*/
    public Path load(String locationFilename) {
        Path location = Paths.get(locationFilename);
        return location.resolve(locationFilename);
    }
    /*허용할 파일 체크*/
    public boolean checkFileType(MultipartFile multipartFile) {
        //파일명구하기
        String orgname = multipartFile.getOriginalFilename();
        //확장자 구하기
        String fileType = orgname.substring(orgname.lastIndexOf(".")+1, orgname.length());
        return checkFileList.stream().filter(df->fileType.equals(df)).findAny().isPresent();
    }
    /*파일 명만 가져오기(패스가 포함되어있을 경우 파일 명만 추출함)*/
    public String getFileName(String locaion){
        Path path = Paths.get(locaion);
        return path.getFileName().toString();
    }

}
