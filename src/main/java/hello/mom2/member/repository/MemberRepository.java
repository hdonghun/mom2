package hello.mom2.member.repository;

import hello.mom2.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member save(Member member);
    Optional<Member> findById(String id);
    Optional<Member> findByUsername(String username);
    List<Member> findAll();
}