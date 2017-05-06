package com.phogram.service;

/**
 * Created by gavin on 2017. 5. 6..
 */
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Path;

public interface FileStorageService {
    boolean save(MultipartFile file,String account);
    Path load(String filename);
    Resource loadAsResource(String filename);
    boolean deleteByFileName(String filename);
}