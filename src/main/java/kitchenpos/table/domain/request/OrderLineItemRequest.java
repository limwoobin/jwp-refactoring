package kitchenpos.table.domain.request;

public class OrderLineItemRequest {
    private Long menuId;
    private long quantity;

    public OrderLineItemRequest(Long menuId, long quantity) {
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public Long getMenuId() {
        return menuId;
    }

    public long getQuantity() {
        return quantity;
    }
}
