package kitchenpos.product.domain;

import kitchenpos.product.exception.InputProductDataErrorCode;
import kitchenpos.product.exception.InputProductDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("상품 단위 테스트")
class ProductTest {

    @Test
    @DisplayName("상품을 등록한다.")
    void createProductTest() {
        Product product = new Product("대파치킨", new BigDecimal("10000"));
        checkValidProduct(product, "대파치킨", new BigDecimal("10000"));
    }

    @Test
    @DisplayName("잘못된 상품 가격을 입력하면 예외처리")
    void createWrongPriceProductTest() {
        assertThatThrownBy(() -> {
            new Product("대파치킨", new BigDecimal(-10000));
        }).isInstanceOf(InputProductDataException.class)
                .hasMessageContaining(InputProductDataErrorCode.IT_CAN_NOT_INPUT_PRICE_LESS_THAN_ZERO.errorMessage());
    }

    private void checkValidProduct(Product product, String name, BigDecimal price) {
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualTo(price);
    }

}