package com.phogram.service.impl;


import com.phogram.config.StorageException;
import com.phogram.config.StorageFileNotFoundException;
import com.phogram.service.FileStorageService;
import com.phogram.utils.PGFileHandler;
import com.phogram.utils.PGTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * Created by gavin on 2017. 5. 6..
 */

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private static final Logger log = LoggerFactory.getLogger(FileStorageServiceImpl.class);

    @Autowired
    private PGFileHandler fileHandler;

    @Value("${phogram.file.location}")
    private String rootLocation;

    @Override
    public boolean save(MultipartFile file,String account) {
        try {
            if (file.isEmpty()) {
                log.error("save: Failed to store empty file"+file.getOriginalFilename());
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            //허용 파일인지 체크
            if(!fileHandler.checkFileType(file)){
                return false;
            }else{
                //저장할 경로 생성
                Path path = fileHandler.getRootDirPath(rootLocation+"/"+account+"/"+PGTimeUtil.nowFormatToString("yyyyMMddHHmmss")+"/");
                Files.copy(file.getInputStream(), path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING );
            }
            return true;
        } catch (IOException e) {
            log.error("save: "+file.getOriginalFilename()+": "+e);
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Path load(String filename) {
        return fileHandler.getRootDirPath(rootLocation).resolve(filename);
    }

    /*전체 경로를 인자로 전달한다*/
    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                log.error("loadAsResource-> Could not read file: "+filename);
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            log.error("loadAsResource->Could not read file: "+e);
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    /*전체 경로를 인자로 전달한다*/
    @Override
    public boolean deleteByFileName(String filename) {
        Path file = load(filename);
        try {
            return Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

}