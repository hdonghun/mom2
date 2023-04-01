package hello.mom2.mainpost.controller;

import hello.mom2.mainpost.domain.Post;
import hello.mom2.mainpost.repository.PostRepository;
import hello.mom2.mainpost.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostServiceImpl postService;

    @GetMapping("/posts/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "posts/detail";
    }
    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/list";
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/posts/create")
    public String create() {
        return "posts/create";
    }
    @PostMapping("/posts/create")
    public String store(@ModelAttribute Post post) {
        postService.save(post);
        System.out.println("게시글 작성 등록이 성공하였습니다.");
        return "redirect:/posts/list";
    }


}

