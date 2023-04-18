package hello.mom2.member.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private String city;
    private String street;
    private String zipcode;


}
