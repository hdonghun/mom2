package hello.mom2.member.repository;


import hello.mom2.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext // JPA제공하는 표준 어노테이션인 PersistenceContext
    private final EntityManager em;  // Spring이  EntityManager을 를 em에 inject해준다.

    public void save(Member member){

        em.persist(member);
    }

    public Member findOne(Long id) {

        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String username){ //파라미터를 바인딩해서, 특정회원의 이름만 참고해서 가져오는거,
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getResultList();
    }

    public List<Object[]> needInfo(String username) {
        return em.createQuery("select m.username, m.email, m.phoneNumber from Member m where m.username = :username", Object[].class)
                .setParameter("username", username)
                .getResultList();
    }



}
