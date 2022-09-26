package testone;

import java.util.List;
import java.util.Set;

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
		Member member = new Member();
		member.setName("name1");
		member.setAddress(new Address("city1" ,"street1","zipcode1"));
		
		member.getFavoriteFoods().add("피자");
		member.getFavoriteFoods().add("치킨");
		member.getFavoriteFoods().add("돈까스");
		
		member.getAddressHistory().add(new Address("old2","street2","street2"));
		member.getAddressHistory().add(new Address("old3","street3","street3"));
		
		em.persist(member);
		
		em.flush();
		em.clear();
		System.out.println("================ START =================");
		Member m = em.find(Member.class, member.getId());
		
//		Address address = m.getAddress();
//		m.setAddress(new Address("city12345",address.getStreet(),address.getZipcode()));
		
//		m.getFavoriteFoods().remove("치킨");
//		m.getFavoriteFoods().add("한식");
		
		m.getAddressHistory().remove(new Address("old2","street2","street2"));
		m.getAddressHistory().add(new Address("city5","street2","street2"));
		
//		Set<String> foods  = m.getFavoriteFoods();
//		for(String food : foods) {
//			System.out.println(food);
//		}
//		List<Address> addressHistorys = m.getAddressHistory();
//				
//		for(Address address : addressHistorys) {		
//			System.out.println(address.getCity());
//		}
		
		tx.commit();
	}catch(Exception e){
		tx.rollback();
	}finally{
		em.close();
	}
	
	emf.close();
	
	}
}
