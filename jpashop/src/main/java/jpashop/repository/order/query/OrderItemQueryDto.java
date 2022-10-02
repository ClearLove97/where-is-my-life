package jpashop.repository.order.query;

import lombok.Data;

@Data
public class OrderItemQueryDto {

    private Long orderId;
    private String itemName;
    private int orderprice;
    private int count;

    public OrderItemQueryDto(Long orderId, String itemName, int orderprice, int count) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderprice = orderprice;
        this.count = count;
    }
}
