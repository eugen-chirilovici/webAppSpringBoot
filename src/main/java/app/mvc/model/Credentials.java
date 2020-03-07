package app.mvc.model;

import app.mvc.model.enums.RoleType;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    private long id;
    private String username;
    private String password;
    private RoleType role;
}
