package kitchenpos.menu.ui;

import kitchenpos.menu.application.ProductService;
import kitchenpos.menu.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(final ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody final Product product) {
        final Product created = productService.create(product);
        final URI uri = URI.create("/api/products/" + created.getId());
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok().body(productService.list());
    }

    @PostMapping("/copy")
    public ResponseEntity<Product> createCopy(@RequestBody final Product product) {
        final Product created = productService.createCopy(product);
        final URI uri = URI.create("/api/products/" + created.getId());
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/copy")
    public ResponseEntity<List<Product>> listCopy() {
        return ResponseEntity.ok().body(productService.listCopy());
    }
}
