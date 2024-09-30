package com.sorune.photogrampj.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.sorune.photogrampj.repository.jpa")
public class JpaConfig {
}
