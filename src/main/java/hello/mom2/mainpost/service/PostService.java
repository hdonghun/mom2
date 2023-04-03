package hello.mom2.mainpost.service;

import hello.mom2.mainpost.domain.Post;
import hello.mom2.mainpost.dto.PostDto;
import hello.mom2.mainpost.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post create(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUsername(postDto.getUsername());
        return postRepository.save(post);
    }

}
