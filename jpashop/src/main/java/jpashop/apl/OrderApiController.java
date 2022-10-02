package jpashop.apl;

import jpashop.domain.Address;
import jpashop.domain.Order;
import jpashop.domain.OrderItem;
import jpashop.domain.OrderStatus;
import jpashop.domain.item.Item;
import jpashop.repository.OrderRepository;
import jpashop.repository.OrderSearch;
import jpashop.repository.order.query.OrderFlatDto;
import jpashop.repository.order.query.OrderQueryDto;
import jpashop.repository.order.query.OrderQueryRepository;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderRepository orderRepository;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/api/v1/orders")
    public List<Order> orderV1(){ // Entity 를 직접 노출
        List<Order> all =  orderRepository.findAllByString(new OrderSearch());
        for(Order order: all){
            order.getMember().getName();
            order.getDelivery().getAddress();
           List<OrderItem> orderItems = order.getOrderItems();
           orderItems.stream().forEach(o -> o.getItem().getName());
        }
        return all;
    }
    @GetMapping("api/v2/orders")
    public List<OrderDto> ordersV2(){    // Entity를 DTO로 변환하여 반환
       List<Order> orders = orderRepository.findAllByString(new OrderSearch());
       List<OrderDto> orderDto = orders.stream().map(o -> new OrderDto(o)).collect(Collectors.toList());

       return orderDto;
    }

    @GetMapping("api/v3/orders")
    public List<OrderDto> ordersV3(){ // 페치조인 으로 디비성능 최적화.
        List<Order> orders = orderRepository.findAllWithItem();

        List<OrderDto> result = orders.stream().map(o -> new OrderDto(o)).collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v4/orders")
    public List<OrderQueryDto> ordersV4(){
      return  orderQueryRepository.findOrderQueryDtos();
    }

    @GetMapping("/api/v5/orders")
    public List<OrderQueryDto> ordersV5(){
        return orderQueryRepository.findAllByDto_optimization();
    }

    @GetMapping("/api/v6/orders")
    public List<OrderFlatDto> ordersV6(){
        return orderQueryRepository.findAllByDto_flat();
    }
    @Getter
    static class OrderDto{

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;
        private List<OrderItemDto> orderItem;

        public OrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
            order.getOrderItems().stream().forEach(o -> o.getItem().getName());
            orderItem = order.getOrderItems().stream().map(OrderItem -> new OrderItemDto(OrderItem)).collect(Collectors.toList());
        }
    }
    @Data
    static class OrderItemDto{
            private String itemName;
            private int orderPrice;
            private int count;

            public OrderItemDto(OrderItem orderItem){
                itemName = orderItem.getItem().getName();
                orderPrice = orderItem.getItem().getPrice();
                count = orderItem.getCount();
            }
    }
}
