package hello.mom2.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MemberRequestDto {
    @Getter
    @Setter
    public static class Signup {
        private String username;
        private String email;
        private String password;
    }

    @Getter
    @Setter
    public static class Login {
        private String username;
        private String email;
        private String password;
    }


    public String getUsername() {
        return getUsername();
    }

    public String getPassword() {
        return getPassword();
    }


}