package com.board.service.impl;

import com.board.domain.Member;
import com.board.mapper.MemberMapper;
import com.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder; // 빈으로 등록 필요

    @Override
    public void register(Member member) {
        // 비밀번호 암호화
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberMapper.insertMember(member);
    }

    @Override
    public Member login(String email, String plainPassword) {
        Member member = memberMapper.findByEmail(email);
        if (member == null) return null;
        if (passwordEncoder.matches(plainPassword, member.getPassword())) {
            return member;
        }
        return null;
    }

    @Override
    public Member findByEmail(String email) {
        return memberMapper.findByEmail(email);
    }

    // ---------------------------
    
    // UserDetailsService 구현
    // ---------------------------
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.findByEmail(username);
        if (member == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        // UserDetails 반환
        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getEmail())       // 로그인 시 이메일을 사용
                .password(member.getPassword())    // 암호화된 비밀번호
                .roles("USER")                     // 필요 시 권한 설정
                .build();
    }
}
