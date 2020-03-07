package app.mvc.util;

import app.mvc.dto.UserDTO;
import app.mvc.model.User;

public class AppUtils {
    public static UserDTO userConvert(User user) {
        return UserDTO.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
