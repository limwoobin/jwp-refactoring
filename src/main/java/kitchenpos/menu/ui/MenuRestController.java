package kitchenpos.menu.ui;

import kitchenpos.menu.application.MenuService;
import kitchenpos.menu.domain.request.MenuRequest;
import kitchenpos.menu.domain.response.MenuResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuRestController {

    private final MenuService menuService;

    public MenuRestController(final MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<MenuResponse> createCopy(@RequestBody final MenuRequest menuRequest) {
        final MenuResponse created = menuService.create(menuRequest);
        final URI uri = URI.create("/api/menus/" + created.getId());
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> listCopy() {
        return ResponseEntity.ok().body(menuService.list());
    }
}
