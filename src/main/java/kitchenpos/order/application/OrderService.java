package kitchenpos.order.application;

import kitchenpos.core.exception.ExceptionType;
import kitchenpos.core.exception.NotFoundException;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.domain.request.OrderRequest;
import kitchenpos.order.domain.response.OrderResponse;
import kitchenpos.table.domain.OrderTable;
import kitchenpos.table.domain.OrderTableRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderTableRepository orderTableRepository;
    private final OrderValidator orderValidator;

    public OrderService(OrderRepository orderRepository, OrderTableRepository orderTableRepository, OrderValidator orderValidator) {
        this.orderRepository = orderRepository;
        this.orderTableRepository = orderTableRepository;
        this.orderValidator = orderValidator;
    }

    @Transactional
    public OrderResponse create(final OrderRequest orderRequest) {
        Order order = Order.of(orderValidator, orderRequest);
        order = orderRepository.save(order);

        OrderTable orderTable = orderTableRepository.findById(order.getOrderTableId())
            .orElseThrow(() -> new NotFoundException(ExceptionType.NOT_EXIST_ORDER_TABLE));
        return OrderResponse.of(order, orderTable);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> list() {
        final List<Order> orders = orderRepository.findAllOrderAndItems();

        return orders.stream()
            .map(OrderResponse::toResponseWithoutOrderTable)
            .collect(Collectors.toList());
    }

    @Transactional
    public OrderResponse changeOrderStatus(final Long orderId, final OrderRequest orderRequest) {
        Order savedOrder = orderRepository.findById(orderId)
            .orElseThrow(() -> new NotFoundException(ExceptionType.NOT_EXIST_ORDER.getMessage(orderId)));

        savedOrder.changeOrderStatus(orderRequest.getOrderStatus());
        orderRepository.save(savedOrder);

        savedOrder = orderRepository.findAllOrderAndItemsByOrder(savedOrder);
        return OrderResponse.toResponseWithoutOrderTable(savedOrder);
    }
}
