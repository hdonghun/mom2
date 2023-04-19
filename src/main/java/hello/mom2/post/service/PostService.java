package hello.mom2.post.service;

import hello.mom2.post.domain.Post;
import hello.mom2.post.domain.PostForm;
import hello.mom2.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public void createPostForm(PostForm postForm, Long userId) {
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setCreatedAt(LocalDateTime.now());
        post.setAuthor(userId);
        postRepository.save(post);
    }

}
