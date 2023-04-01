package hello.mom2.mainpost.service;

import hello.mom2.mainpost.domain.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<Post> findAll();

}
