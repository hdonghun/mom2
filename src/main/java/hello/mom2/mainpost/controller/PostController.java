package hello.mom2.mainpost.controller;

import hello.mom2.mainpost.domain.Post;
import hello.mom2.mainpost.domain.PostDto;
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
    @GetMapping("/posts")
    public String list(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "posts/list";
    }
    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        // id에 해당하는 게시글을 DB에서 조회하여 model에 저장
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid post id"));
        model.addAttribute("post", post);

        return "posts/detail"; // 상세 내용을 보여줄 view의 이름을 리턴
    }
    @GetMapping("/posts/create")
    public String createForm(Model model) {
        model.addAttribute("postDto", new PostDto());
        return "posts/createForm";
    }
    @PostMapping("posts/createForm")
    public String create(@ModelAttribute PostDto postDto) {
        postService.create(postDto);
        return "redirect:/posts/list";
    }

}