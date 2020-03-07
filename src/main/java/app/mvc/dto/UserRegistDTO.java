package app.mvc.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class UserRegistDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String username;
}
