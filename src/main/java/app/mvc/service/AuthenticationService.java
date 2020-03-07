package app.mvc.service;

import app.mvc.dao.CredentialsDAO;
import app.mvc.dto.CredentialsDTO;
import app.mvc.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private CredentialsDAO credentialsDAO;

    public Credentials confirmAuthentication(CredentialsDTO credentials) {
        Optional<Credentials> credentialsOfUser = credentialsDAO.validateUser(credentials);
        return credentialsOfUser.orElse(null);
    }

    public CredentialsDAO getCredentialsDAO() {
        return credentialsDAO;
    }

    public void setCredentialsDAO(CredentialsDAO credentialsDAO) {
        this.credentialsDAO = credentialsDAO;
    }
}
