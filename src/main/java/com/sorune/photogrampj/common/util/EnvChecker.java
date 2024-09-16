package com.sorune.photogrampj.common.util;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EnvChecker {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void checkEnvVariables() {
        String uploadDir = environment.getProperty("UPLOAD_DIR");
        log.info("UPLOAD_DIR: {}", uploadDir);
        uploadDir = System.getenv("UPLOAD_DIR");
        log.info("UPLOAD_DIR from System.getenv: {}", uploadDir);
    }
}
