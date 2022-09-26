package jpql;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		try {
			Team teamA = new Team();
			teamA.setName("TeamA");
			
			Team teamB = new Team();
			teamB.setName("TeamB");
			
			Member member1 = new Member();
			member1.setUsername("회원1");
			member1.setTeam(teamA);
			
			Member member2 = new Member();
			member2.setUsername("회원2");
			member2.setTeam(teamA);
			
			Member member3 = new Member();
			member3.setUsername("회원3");
			member3.setTeam(teamB);
			
			em.persist(teamA);
			em.persist(teamB);
			em.persist(member1);
			em.persist(member2);
			em.persist(member3);
			
			em.flush();
			em.clear();
			
		Member member =	em.createNamedQuery("Member.findByUsername", Member.class)
				.setParameter("username", "회원1")
				.getSingleResult();
		System.out.println(member);
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
		}
		emf.close();
	}
}
