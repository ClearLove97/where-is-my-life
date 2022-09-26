package jpabook.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.domain.Delivery;
import jpabook.domain.Member;
import jpabook.domain.Order;
import jpabook.domain.OrderItem;
import jpabook.item.Item;
import jpabook.repository.ItemRepository;
import jpabook.repository.MemberRepository;
import jpabook.repository.OrderRepository;
import jpabook.repository.OrderSearch;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final ItemRepository itemRepository;
	
	//주문
	@Transactional
	public Long order(Long memberId , Long ItemId, int count) {
		Member member = memberRepository.findOne(memberId);
		Item item = itemRepository.findOne(ItemId);
	
		//배송정보 생성
		Delivery delivery = new Delivery();
		delivery.setAddress(member.getAddress());
		//주문상품 생성
		OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
		//주문 생성
		Order order = Order.createOrder(member, delivery, orderItem);
		//주문 저장
		orderRepository.save(order);
		return order.getId();
	}
	
	//주문 취소
	@Transactional
	public void cancelOrder(Long orderId) {
		//주문 엔티티 조회
		Order order =orderRepository.findOne(orderId);
		//주문 취소
		order.cancel();
	}
	
	//검색
	public List<Order> findOrders(OrderSearch orderSearch){
		return orderRepository.findAll(orderSearch);
	}
	
}
