package com.phogram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class PhogramApplication {

	@Value("${phogram.file.location}")
	private String rootLocation;
	/* 루트 파일 저장 경로 체크 및 생성*/
	@PostConstruct
	public void checkAndCreateDir() throws IOException {
		Path locationPath = Paths.get(rootLocation);
		if(Files.notExists(locationPath)){
			Files.createDirectory(locationPath);
		}
	}
	public static void main(String[] args) {
		SpringApplication.run(PhogramApplication.class, args);
	}
}
