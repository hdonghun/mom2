package hello.mom2.member.controller;

import hello.mom2.member.domain.Member;
import hello.mom2.member.service.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

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
        System.out.println("회원가입을 성공하였습니다.");
//        return "redirect:/";
        return "redirect:/?signup_success=true";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/member/login")
    public String getLogin(){
        return "login";  //login.html에 대한 화면 이동
    }
    @PostMapping("/member/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        boolean result = memberService.authenticate(username, password);
        if (result) {
            // 로그인에 성공한 경우, 사용자 정보를 세션에 저장
            session.setAttribute("username", username);
            System.out.println("로그인을 성공하였습니다.");
            return "redirect:/member/afterLogin";
        } else {
            model.addAttribute("error", "아이디나 비밀번호가 일치하지 않습니다.");
            return "/member/login";
        }
    }
    //////////////////////////////////////////////////////////////////////////////
    @GetMapping("/member/afterLogin")
    public String afterLogin(){
        return "afterLogin";  //afterLogin.html에 대한 화면 이동
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // 로그아웃 처리
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId"); // 세션에서 userId 속성 제거
        System.out.println("로그아웃을 성공하였습니다.");
        return "redirect:/"; // 로그인 페이지로 이동
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping("/redirect")
    public RedirectView redirectExample() {
        return new RedirectView("http://localhost:8080/");
    }



}
