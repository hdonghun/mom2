package hello.mom2.member.controller;


import hello.mom2.member.domain.Address;
import hello.mom2.member.domain.Member;
import hello.mom2.member.domain.MemberForm;
import hello.mom2.member.repository.MemberRepository;
import hello.mom2.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    @Autowired
    private final MemberRepository memberRepository;
    private final MemberService memberService;
// 회원가입
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setUsername(form.getUsername());
        member.setPassword(form.getPassword());
        member.setEmail(form.getEmail());
        member.setPhoneNumber(form.getPhoneNumber());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }



// 로그인
    @GetMapping("/members/login")
    public String loginForm() {
        return "members/login";
    }
    @PostMapping("/members/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        RedirectAttributes redirectAttributes, HttpSession session) {
        // DB에서 입력받은 username과 일치하는 사용자 정보 조회
        List<Member> member = memberRepository.findByName(username);
        if (!member.isEmpty() && member.get(0).getPassword().equals(password)) { // 사용자 정보가 존재하고 비밀번호가 일치하는 경우
            session.setAttribute("username", username);
            System.out.println("로그인 성공");
            return "redirect:/";
        } else { // 사용자 정보가 존재하지 않거나 비밀번호가 일치하지 않는 경우
            redirectAttributes.addFlashAttribute("error", "사용자 정보가 존재하지 않거나 비밀번호가 일치하지 않는 경우.");
            return "redirect:/members/login";
        }
    }


    // 로그인 정보를 세션 저장?
    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        if (principal != null) {
            // 로그인이 되었다면 로그인 버튼을 숨깁니다
            model.addAttribute("currentUser", true);
        } else {
            // 로그인이 안되었다면 로그인 버튼을 보여줍니다
            model.addAttribute("currentUser", false);
        }
        return "index";
    }





    // 나중에 관리자만 할 수 있도록
    // 회원 조회
//    @GetMapping(value = "/members")
//    public String list(Model model) {
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
//        return "members/memberList";
//    }

}