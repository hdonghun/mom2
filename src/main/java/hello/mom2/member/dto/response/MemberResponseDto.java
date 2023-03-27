//package hello.mom2.member.dto.response;
//
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.Builder;
//import lombok.Getter;
//
//import java.time.LocalDateTime;
//
//@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class MemberResponseDto {
//    private Long memberNo;
//
//    private String email;
//
//    private String nick;
//
//    @Builder
//    public MemberResponseDto(Long memberNo, String email, String nick) {
//        this.memberNo = memberNo;
//        this.email = email;
//        this.nick = nick;
//    }
//}