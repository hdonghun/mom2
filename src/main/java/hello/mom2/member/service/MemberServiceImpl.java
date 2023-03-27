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
    public Member join(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByUsername(member.getUsername())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> findMemberByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
