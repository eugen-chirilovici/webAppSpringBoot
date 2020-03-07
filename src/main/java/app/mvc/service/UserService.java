package app.mvc.service;

import app.mvc.dao.CredentialsDAO;
import app.mvc.dao.UsersDAO;
import app.mvc.model.Credentials;
import app.mvc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private CredentialsDAO credentialsDAO;

    public List<User> getAllUsers() {
        return UsersDAO.getListOfUsers();
    }

    public User getUserById(Long userId) {
        return usersDAO
                .findUserById(userId)
                .orElse(null);
    }

    public User getUserByCredentials(Credentials userCredentials) {
        return usersDAO
                .findUserByCredentialsId(userCredentials.getId())
                .orElse(null);
    }

    public User getUserByUserName(String username) {
        Optional<Credentials> userByScreenName = credentialsDAO.findByUsername(username);
        return userByScreenName
                .map(credentials ->
                        usersDAO.findUserByCredentialsId(credentials.getId())
                                .orElse(null))
                .orElse(null);
    }
}
