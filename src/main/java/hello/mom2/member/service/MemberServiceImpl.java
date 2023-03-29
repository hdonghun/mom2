package hello.mom2.member.service;

import hello.mom2.member.domain.Member;
import hello.mom2.member.dto.request.MemberRequestDto;
import hello.mom2.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
//    private final PasswordEncoder passwordEncoder; // 스프링 security


    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
//////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Member sign(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }
    private void validateDuplicateMember(Member member) {
        memberRepository.findByUsername(member.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원이름입니다.");
                });
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원이메일입니다.");
                });
    }

    //////////////////////////////////////////////////////////////////////////////////////


    public boolean authenticate(String username, String password) {
        Optional<Member> member = memberRepository.findByUsername(username);
        return member.filter(m -> m.getPassword().equals(password)).isPresent();
    }





}

