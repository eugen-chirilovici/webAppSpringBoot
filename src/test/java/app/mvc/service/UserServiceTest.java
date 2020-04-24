package app.mvc.service;

import app.mvc.dao.CredentialsDAO;
import app.mvc.dao.UsersDAO;
import app.mvc.model.Credentials;
import app.mvc.model.User;
import app.mvc.util.CredentialsUtils;
import app.mvc.util.UserUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.event.annotation.PrepareTestInstance;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UsersDAO usersDAO;

    @Mock
    private CredentialsDAO credentialsDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
       verifyNoMoreInteractions(usersDAO);
    }

    @Test
    public void shouldGetUserById() {
        final User expectedUser = UserUtils.createUser();

        when(usersDAO.findUserById(expectedUser.getUserId())).thenReturn(Optional.of(expectedUser));

        final User actualUser = userService.getUserById(expectedUser.getUserId());

        verify(usersDAO).findUserById(expectedUser.getUserId());
        assertEquals(expectedUser, actualUser);


    }


    @Test
    public void shouldGetUserByCredentials() {
        final Credentials credentialsForExpectedUser = CredentialsUtils.createCredentialsForUserRole();
        final User expectedUser = UserUtils.createUser();

        when(usersDAO.findUserByCredentialsId(credentialsForExpectedUser.getId())).thenReturn(Optional.of(expectedUser));

        final User actualUser = userService.getUserByCredentials(credentialsForExpectedUser);

        verify(usersDAO).findUserByCredentialsId(expectedUser.getCredentialsId());
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void shouldGetUserByUserName() {
        final Credentials credentialsForExpectedUser = CredentialsUtils.createCredentialsForUserRole();
        final User expectedUser = UserUtils.createUser();

        when(credentialsDAO.findByUsername(credentialsForExpectedUser.getUsername())).thenReturn(Optional.of(credentialsForExpectedUser));
        when(usersDAO.findUserByCredentialsId(credentialsForExpectedUser.getId())).thenReturn(Optional.of(expectedUser));

        final User actualUser = userService.getUserByUserName(credentialsForExpectedUser.getUsername());

        verify(credentialsDAO).findByUsername(credentialsForExpectedUser.getUsername());
        verify(usersDAO).findUserByCredentialsId(credentialsForExpectedUser.getId());
        verifyNoMoreInteractions(credentialsDAO);
        assertEquals(expectedUser, actualUser);
    }
}
