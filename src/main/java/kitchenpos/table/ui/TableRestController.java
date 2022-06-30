package kitchenpos.table.ui;

import kitchenpos.table.application.OrderTableService;
import kitchenpos.table.dto.request.OrderTableRequest;
import kitchenpos.table.dto.response.OrderTableResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tables")
public class TableRestController {

    private final OrderTableService orderTableService;

    public TableRestController(final OrderTableService orderTableService) {
        this.orderTableService = orderTableService;
    }

    @PostMapping
    public ResponseEntity<OrderTableResponse> create(
        @RequestBody final OrderTableRequest orderTableRequest) {
        final OrderTableResponse created = orderTableService.create(orderTableRequest);
        final URI uri = URI.create("/api/tables/" + created.getId());
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping
    public ResponseEntity<List<OrderTableResponse>> list() {
        return ResponseEntity.ok().body(orderTableService.list());
    }

    @PutMapping("/{orderTableId}/empty")
    public ResponseEntity<OrderTableResponse> changeEmpty(
        @PathVariable final Long orderTableId) {
        return ResponseEntity.ok().body(orderTableService.changeEmpty(orderTableId));
    }

    @PutMapping("/{orderTableId}/number-of-guests")
    public ResponseEntity<OrderTableResponse> changeNumberOfGuests(
        @PathVariable final Long orderTableId, @RequestBody final OrderTableRequest orderTableRequest) {
        return ResponseEntity.ok()
            .body(orderTableService.changeNumberOfGuests(orderTableId, orderTableRequest));
    }
}
