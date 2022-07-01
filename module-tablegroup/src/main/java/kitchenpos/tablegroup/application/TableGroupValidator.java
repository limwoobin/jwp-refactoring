package kitchenpos.tablegroup.application;

import kitchenpos.core.exception.BadRequestException;
import kitchenpos.core.exception.CannotCreateException;
import kitchenpos.core.exception.ExceptionType;
import kitchenpos.table.domain.OrderTable;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class TableGroupValidator {

    public void validate(List<Long> orderTableIds, List<OrderTable> orderTables) {
        validateOrderTableSize(orderTableIds);
        validateOrderTableEqualsSize(orderTables, orderTableIds);
        validateTablesEmpty(orderTables);
    }

    private void validateOrderTableSize(List<Long> orderTableIds) {
        if (CollectionUtils.isEmpty(orderTableIds) || orderTableIds.size() < 2) {
            throw new CannotCreateException(ExceptionType.ORDER_TABLE_AT_LEAST_TWO);
        }
    }

    private void validateTablesEmpty(List<OrderTable> items) {
        items.stream()
            .filter(it -> !it.isEmpty() || it.isGrouped())
            .findFirst()
            .ifPresent(e -> {
                throw new CannotCreateException(ExceptionType.MUST_NOT_BE_EMPTY_OR_GROUPED_TABLE);
            });
    }

    private void validateOrderTableEqualsSize(List<OrderTable> savedOrderTables, List<Long> orderTableIds)  {
        if (savedOrderTables.size() != orderTableIds.size()) {
            throw new BadRequestException(ExceptionType.CONTAINS_NOT_EXIST_ORDER_TABLE);
        }
    }
}
