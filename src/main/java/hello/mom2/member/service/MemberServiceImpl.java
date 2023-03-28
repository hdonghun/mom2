package hello.mom2.member.service;

import hello.mom2.member.domain.Member;
import hello.mom2.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public Member sign(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    @Override
    public Member login(Member member) {
        memberRepository.findById(member.getId());
        if (member != null && member.getPassword().equals(login(member))) {
            return member;
        } else {
            return null;
        }
    }


    private void validateDuplicateMember(Member member) {
        memberRepository.findByMemberName(member.getMembername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원이름입니다.");
                });
        memberRepository.findByEmail(member.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원이메일입니다.");
                });
    }



}
