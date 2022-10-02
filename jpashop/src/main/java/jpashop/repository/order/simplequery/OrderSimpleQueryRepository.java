package jpashop.repository.order.simplequery;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {
	
	private final EntityManager em;

	public List<OrderSimpleQueryDto> findOrderDtos(){
		return em.createQuery("select new jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
				" from Order o" +
				" join fetch o.member m" +
				" join fetch o.delivery d", OrderSimpleQueryDto.class).getResultList();
	}
	
	
}
