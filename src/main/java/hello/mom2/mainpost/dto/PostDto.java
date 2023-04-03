package hello.mom2.mainpost.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDto {
    private String title;
    private String content;
    private String username;

    // 생성자, getter, setter 생략
}
