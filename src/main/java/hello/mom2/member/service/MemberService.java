package hello.mom2.member.service;


import hello.mom2.member.domain.Member;
import hello.mom2.member.dto.request.MemberRequestDto;
import org.springframework.stereotype.Service;


@Service
public interface MemberService {
    Member sign(Member member);
//    Member login(Member member);


}
