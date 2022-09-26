package hellojpa;



import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	
	public static void main(String[] args)  {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		
		EntityManager em = emf.createEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			String jpql = "select m From Member m where m.username like '%hello%'";
			em.createQuery(jpql, Member.class)
			.getResultList();
			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally {
			em.close();		
		}
		emf.close();
		
	}
}
