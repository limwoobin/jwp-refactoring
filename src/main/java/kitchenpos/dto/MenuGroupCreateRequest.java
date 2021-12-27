package kitchenpos.dto;

import javax.validation.constraints.NotBlank;

public class MenuGroupCreateRequest {

    @NotBlank
    private String name;

    public MenuGroupCreateRequest() {
    }

    public MenuGroupCreateRequest(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}