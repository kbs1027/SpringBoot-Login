package com.member.controller;

import com.member.dto.MemberDTO;
import com.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
// 생성자 주입
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;
    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        System.out.println("post login");
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null){
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            return "login";
        }
    }
}
