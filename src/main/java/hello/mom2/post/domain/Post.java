package hello.mom2.post.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 게시물 아이디
    private String title; // 게시물 제목
    private String content; // 게시물 내용
    private Long author; // 게시물 작성자
    private LocalDateTime createdAt; // 게시물 작성일

    // 생성자, getter, setter 등 필요한 메서드 구현
    // ...
}
