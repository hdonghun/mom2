package hello.mom2.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Member {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;

}
