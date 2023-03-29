package hello.mom2.member.controller;

import hello.mom2.member.domain.Member;
import hello.mom2.member.dto.request.MemberRequestDto;
import hello.mom2.member.service.MemberService;
import hello.mom2.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    private final MemberServiceImpl memberService;

    @Autowired
    public MemberController(MemberServiceImpl memberService) {
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
        return "index";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/member/login")
    public String getLogin(){
        return "login";  //login.html에 대한 화면 이동
    }
    @PostMapping("/member/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean result = memberService.authenticate(username, password);
        if (result) {
            return "redirect:/";
        } else {
            return "login";
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/member/logout")
    public String getLogout(HttpSession session) {
        session.removeAttribute("member");
        return "redirect:";
    }




}
