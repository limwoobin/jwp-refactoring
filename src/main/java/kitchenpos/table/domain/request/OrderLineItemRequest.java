package kitchenpos.table.domain.request;

import kitchenpos.order.domain.OrderLineItem;

public class OrderLineItemRequest {
    private Long menuId;
    private long quantity;

    public OrderLineItemRequest(Long menuId, long quantity) {
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public static OrderLineItem toEntity(OrderLineItemRequest request) {
        return OrderLineItem.of(null, null, request.getMenuId(), request.getQuantity());
    }

    public Long getMenuId() {
        return menuId;
    }

    public long getQuantity() {
        return quantity;
    }

}
