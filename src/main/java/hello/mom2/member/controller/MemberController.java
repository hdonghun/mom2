package hello.mom2.member.controller;

import hello.mom2.member.domain.Member;
import hello.mom2.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/member/signup")
    public String getSign(){
        return"signup";  //signup.html에 대한 화면 이동
    }

    @PostMapping("/member/signup")
    public String postSignup(Member member) {
        // 회원가입 처리 로직
        memberService.sign(member);
        // redirect URL 반환
        return "redirect:/member/login";
    }

    @GetMapping("/member/login")
    public String getLogin(){
        return"login";  //login.html에 대한 화면 이동
    }

    @PostMapping("/member/login")
    public String postLogin(Member member, HttpSession session) {
        memberService.login(member);
        if (member != null) {
            session.setAttribute("member", member);
            return "redirect:";
        } else {
            return "redirect:/member/login";
        }
    }


    @GetMapping("/member/logout")
    public String getLogout(HttpSession session) {
        session.removeAttribute("member");
        return "redirect:";
    }




}
