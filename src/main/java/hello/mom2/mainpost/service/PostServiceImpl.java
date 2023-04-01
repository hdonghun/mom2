package hello.mom2.mainpost.service;

import hello.mom2.mainpost.domain.Post;
import hello.mom2.mainpost.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    private final List<Post> posts = new ArrayList<>();

    public List<Post> findAll() {
        return posts;
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post findById(Long id) {
        // id로 게시물을 조회하는 로직
        return posts.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + id));
    }




}
