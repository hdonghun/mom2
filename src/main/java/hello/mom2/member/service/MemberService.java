package hello.mom2.member.service;


import hello.mom2.member.domain.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MemberService {
    Member sign(Member member);
    Member login(Member member);
    Optional<Member> findMember(Long id);
    Optional<Member> findMemberByUsername(String username);
    List<Member> findAllMembers();

}
