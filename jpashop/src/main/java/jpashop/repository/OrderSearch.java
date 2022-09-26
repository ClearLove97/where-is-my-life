package jpashop.repository;

import jpashop.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
	
	private String memberName; 	// 회원 이름으로 검색
	private OrderStatus orderStatus;	// 주문 상태 [CANCEL , ORDER]
}
