package app.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private Long credentialsId;
}
