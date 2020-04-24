package app.mvc.util;

import app.mvc.dto.UserDTO;
import app.mvc.model.User;

import java.util.Arrays;
import java.util.List;

public class UserUtils {
    public static User createUser() {
        return User.builder()
                .credentialsId(123456L)
                .firstName("test")
                .lastName("user")
                .userId(1L)
                .build();
    }

    public static List<User> createUserList() {
        final User user1 = User.builder()
                .credentialsId(123456L)
                .firstName("test")
                .lastName("user")
                .userId(1L)
                .build();


        final User user2 = User.builder()
                .credentialsId(987654L)
                .firstName("test")
                .lastName("admin")
                .userId(2L)
                .build();

        return Arrays.asList(user1, user2);
    }

    public static UserDTO userDTO() {
        return UserDTO.builder()
                .userId(1L)
                .firstName("test")
                .lastName("user")
                .build();
                    }
}
