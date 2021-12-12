package kitchenpos.dao;

import kitchenpos.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends ProductDao, JpaRepository<Product, Long> {
}