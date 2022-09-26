package jpashop.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import jpashop.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
	
	private final EntityManager em;
	
	public void save(Member member) { // 회원 생성
		em.persist(member);
	}
	
	public Member findMember(Long id) { // 아이디로 조회
		return em.find(Member.class, id);
	}
	
	public List<Member> findAll(){ // 전체회원 조회
		return em.createQuery("select m from Member m",Member.class).getResultList();	
	}
	
	public List<Member> findByMember(String name){ // 이름으로 회원조회
		return em.createQuery("select m from Member m where m.name = :name",Member.class).setParameter("name", name).getResultList();
	}
}
