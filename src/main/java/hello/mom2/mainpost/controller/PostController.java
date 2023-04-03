package hello.mom2.mainpost.controller;

import hello.mom2.mainpost.domain.Post;
import hello.mom2.mainpost.dto.PostDto;
import hello.mom2.mainpost.repository.PostRepository;
import hello.mom2.mainpost.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;

    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }
    // 게시글 조회
    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/list";
    }
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post id : " + id));
        model.addAttribute("post", post);
        return "posts/detail";
    }

    // 게시글 작성
    @GetMapping("/posts/create")
    public String createForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "posts/createForm";
    }
    @PostMapping("posts/create")
    public String create(@ModelAttribute PostDto postDto) {
        postService.create(postDto);
        return "redirect:/posts";
    }



}