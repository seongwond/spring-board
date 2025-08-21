package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 브라우저에서 / 요청 시 index.jsp로 이동
    @GetMapping("/")
    public String home() {
        return "index"; // WEB-INF/views/index.jsp와 매칭
    }
}
