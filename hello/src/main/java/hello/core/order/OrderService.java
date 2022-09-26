package hello.core.order;

public interface OrderService {
	Order createOreder(Long memberId, String itemName , int itemPrice);
}
