package kitchenpos.menu.acceptance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import kitchenpos.menu.domain.request.MenuRequest;
import kitchenpos.menu.domain.response.MenuResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class MenuAcceptanceSupport {

    public static ExtractableResponse<Response> 메뉴등록을_요청(MenuRequest menuRequest) {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(menuRequest)
            .when().post("/api/menus")
            .then().log().all().
            extract();
    }

    public static void 메뉴_정상_등록됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("Location")).isNotBlank();
    }

    public static ExtractableResponse<Response> 모든메뉴_조회요청() {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/api/menus")
            .then().log().all().
            extract();
    }

    public static void 메뉴목록_정상_조회됨(ExtractableResponse<Response> response, int size) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());

        List<MenuResponse> menus = response.jsonPath().getList(".", MenuResponse.class);
        MenuResponse result = menus.get(0);
        assertAll(
            () -> assertThat(result).isNotNull(),
            () -> assertThat(menus).hasSize(size),
            () -> assertThat(result.getMenuProducts()).hasSize(2),
            () -> assertNotNull(result.getMenuGroupId())
        );
    }
}
