package kitchenpos.table.ui;

import kitchenpos.table.application.TableService;
import kitchenpos.table.domain.request.OrderTableRequest;
import kitchenpos.table.domain.response.OrderTableResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tables")
public class TableRestController {

    private final TableService tableService;

    public TableRestController(final TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping
    public ResponseEntity<OrderTableResponse> create(
        @RequestBody final OrderTableRequest orderTableRequest) {
        final OrderTableResponse created = tableService.create(orderTableRequest);
        final URI uri = URI.create("/api/tables/" + created.getId());
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping
    public ResponseEntity<List<OrderTableResponse>> list() {
        return ResponseEntity.ok().body(tableService.list());
    }

    @PutMapping("/{orderTableId}/empty")
    public ResponseEntity<OrderTableResponse> changeEmpty(
        @PathVariable final Long orderTableId) {
        return ResponseEntity.ok().body(tableService.changeEmpty(orderTableId));
    }

    @PutMapping("/{orderTableId}/number-of-guests")
    public ResponseEntity<OrderTableResponse> changeNumberOfGuests(
        @PathVariable final Long orderTableId, @RequestBody final OrderTableRequest orderTableRequest) {
        return ResponseEntity.ok()
            .body(tableService.changeNumberOfGuests(orderTableId, orderTableRequest));
    }
}
