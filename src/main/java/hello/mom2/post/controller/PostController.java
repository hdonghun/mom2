package hello.mom2.post.controller;

import hello.mom2.member.domain.Member;
import hello.mom2.post.domain.PostForm;
import hello.mom2.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts/new")
    public String createPostForm(Model model, HttpSession session) {
        if (session.getAttribute("username") == null) {
            // 로그인하지 않은 경우 로그인 페이지로 이동
            return "redirect:/members/login";
        }
        model.addAttribute("postForm", new PostForm());
        return "posts/new";
    }

    @PostMapping("/posts/new")
    public String createPost(@ModelAttribute("postForm") PostForm postForm, HttpSession session) {
        Member member  = (Member) session.getAttribute("username");
        postService.createPostForm(postForm, member.getId());
        return "redirect:/";
    }

    //게시물 조회

    // 게시물작성 버튼, 로그인이 되어 있지 않을때는 보여 주지 않기



}
