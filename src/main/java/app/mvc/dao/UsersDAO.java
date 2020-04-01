package app.mvc.dao;

import app.mvc.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsersDAO {

    private static long id = 0L;
    private static List<User> listOfUsers = new ArrayList<>();

    static {
        listOfUsers.add(new User(id++, "Eugen", "Chirilovici", 0L));
        listOfUsers.add(new User(id++, "Ciprian", "Nicuta", 1L));
        listOfUsers.add(new User(id++, "Filip", "Rosca", 2L));
        listOfUsers.add(new User(id++, "Test", "Role", 3L));
    }

    public Long addUser(User user) {
        long userId = id++;
        listOfUsers.add(new User(userId, user.getFirstName(), user.getLastName(), user.getCredentialsId()));
        return userId;
    }

    public Optional<User> findUserById(Long userId) {
        return listOfUsers.stream()
                .filter(t->t.getUserId().equals(userId))
                .findAny();
    }

    public Optional<User> findUserByCredentialsId(long credentialsId) {
        return listOfUsers.stream()
                .filter(t -> t.getCredentialsId().equals(credentialsId))
                .findAny();
    }

    public static List<User> getListOfUsers() {
        return listOfUsers;
    }

    public static void setListOfUsers(List<User> listOfUsers) {
        UsersDAO.listOfUsers = listOfUsers;
    }

}
