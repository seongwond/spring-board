package com.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BoardApplication extends SpringBootServletInitializer {

    // 외장 톰캣 배포를 위한 configure 메서드 오버라이드
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BoardApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }
}
