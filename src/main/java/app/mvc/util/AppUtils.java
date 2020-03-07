package app.mvc.util;

import app.mvc.dto.UserDTO;
import app.mvc.model.User;
    import org.springframework.security.core.context.SecurityContextHolder;

public class AppUtils {
    public static UserDTO userConvert(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public static org.springframework.security.core.userdetails.User getLoggedUser(){
        return (org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
