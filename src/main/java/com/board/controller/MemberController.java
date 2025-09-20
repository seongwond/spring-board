package com.board.controller;

import com.board.domain.Member;
import com.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 폼
    @GetMapping("/register")
    public String showRegisterForm() {
        return "member/register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(
            @ModelAttribute Member member,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        if (member.getPassword() == null || !member.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "비밀번호와 확인이 일치하지 않습니다.");
            return "member/register";
        }

        if (memberService.findByEmail(member.getEmail()) != null) {
            model.addAttribute("error", "이미 사용중인 이메일입니다.");
            return "member/register";
        }

        memberService.register(member);
        return "redirect:/member/login?registered";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String showLoginForm() {
        return "member/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        var member = memberService.login(email, password);
        if (member == null) {
            model.addAttribute("error", "이메일 또는 비밀번호가 올바르지 않습니다.");
            return "member/login";
        }

        // 로그인 성공 시 세션에 사용자 정보 저장
        session.setAttribute("loginMember", member);
        return "redirect:/board/list";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/board/list";
    }
}
