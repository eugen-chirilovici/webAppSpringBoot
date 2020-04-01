package app.mvc.service;

import app.mvc.dao.CredentialsDAO;
import app.mvc.util.CredentialsUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private CredentialsDAO credentialsDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test(expected = UsernameNotFoundException.class)
    public void shouldLoadByUsername() {
        when(credentialsDAO.findByUsername(anyString())).thenThrow(new UsernameNotFoundException("No user found"));
        userDetailsService.loadUserByUsername(anyString());
    }

}
