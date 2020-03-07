package app.mvc.model.enums;

import lombok.Getter;

public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    @Getter
    String value;

    RoleType(String value) {
        this.value = value;
    }
}
