package kitchenpos.table.dto;

import java.time.LocalDateTime;
import java.util.List;
import kitchenpos.table.domain.TableGroup;

public class TableGroupResponse {

    private Long id;
    private LocalDateTime createdDate;
    private List<OrderTableResponse> orderTables;

    public TableGroupResponse() {
        // empty
    }

    private TableGroupResponse(Long id, LocalDateTime createdDate, List<OrderTableResponse> orderTables) {
        this.id = id;
        this.createdDate = createdDate;
        this.orderTables = orderTables;
    }

    public static TableGroupResponse of(final TableGroup tableGroup) {
        return new TableGroupResponse(tableGroup.getId(),
                                      tableGroup.getCreatedDate(),
                                      tableGroup.getOrderTables()
                                                .convertAll(orderTable -> OrderTableResponse.of(tableGroup.getId(),
                                                                                                orderTable)));
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public List<OrderTableResponse> getOrderTables() {
        return orderTables;
    }
}