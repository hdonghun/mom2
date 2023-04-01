package hello.mom2.mainpost.repository;

import hello.mom2.mainpost.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAll();
    Post findById(int id);


}