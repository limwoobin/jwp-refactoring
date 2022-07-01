package kitchenpos.tablegroup.ui;

import kitchenpos.tablegroup.application.TableGroupService;
import kitchenpos.tablegroup.dto.request.TableGroupRequest;
import kitchenpos.tablegroup.dto.response.TableGroupResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/table-groups")
public class TableGroupRestController {

    private final TableGroupService tableGroupService;

    public TableGroupRestController(final TableGroupService tableGroupService) {
        this.tableGroupService = tableGroupService;
    }

    @PostMapping
    public ResponseEntity<TableGroupResponse> create(@RequestBody final TableGroupRequest request) {
        final TableGroupResponse created = tableGroupService.create(request);
        final URI uri = URI.create("/api/table-groups/" + created.getId());
        return ResponseEntity.created(uri).body(created);
    }

    @DeleteMapping("/{tableGroupId}")
    public ResponseEntity<Void> ungroup(@PathVariable final Long tableGroupId) {
        tableGroupService.ungroup(tableGroupId);
        return ResponseEntity.noContent().build();
    }
}
