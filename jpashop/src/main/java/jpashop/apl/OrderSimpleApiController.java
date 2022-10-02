package jpashop.apl;

import jpashop.domain.Address;
import jpashop.domain.Order;
import jpashop.domain.OrderStatus;
import jpashop.repository.OrderRepository;
import jpashop.repository.OrderSearch;
import jpashop.repository.order.simplequery.OrderSimpleQueryDto;
import jpashop.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 
 */


@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
	
	private final OrderRepository orderRepository;
	private final OrderSimpleQueryRepository orderSimpleQueryRepository;
	@GetMapping("/api/v1/simple-orders")
	public List<Order> orderV1(){ // Entity 로 반환
		List<Order> all = orderRepository.findAllByString(new OrderSearch());
		for(Order order : all) {
			order.getMember().getName();	//Lazy 강제초기화
			order.getDelivery().getAddress();	//Lazy 강제초기화
		}
		return all;
	}
	
	@GetMapping("/api/v2/simple-orders")
	public List<OrderSimpleDto> orderV2(){ // DTO 로 변환하여 반환
		List<Order> orders = orderRepository.findAllByString(new OrderSearch());
		
		// 람다식 
		List<OrderSimpleDto> result = orders.stream().map(o -> new OrderSimpleDto(o)).collect(Collectors.toList());
		
		return result;
	}

	@GetMapping("/api/v3/simple-orders")
	public List<OrderSimpleDto> orderV3(){ // Entity로 조회 후  DTO로 변환
		List<Order> orders = orderRepository.findAllWithMemberDelivery();
		List<OrderSimpleDto> result = orders.stream().map(o -> new OrderSimpleDto(o)).collect(Collectors.toList());
		return result;
	}

	@GetMapping("/api/v4/simple-orders")
	public List<OrderSimpleQueryDto> orderV4(){	// DTO로 바로 조회
		return orderSimpleQueryRepository.findOrderDtos();
	}
	
	@Data
	@AllArgsConstructor
	static class OrderSimpleDto{
		private Long orderId;
		private String name;
		private LocalDateTime orderDate;
		private OrderStatus orderStatus;
		private Address address;
		
		public OrderSimpleDto(Order order) {
			orderId = order.getId();
			name = order.getMember().getName();
			orderDate = order.getOrderDate();
			orderStatus = order.getStatus();
			address = order.getDelivery().getAddress();
		}
	}
}
