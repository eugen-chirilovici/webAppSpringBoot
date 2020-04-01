package app.mvc.service;


import app.mvc.dao.CredentialsDAO;
import app.mvc.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CredentialsDAO credentialsDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Credentials> byUsername = credentialsDao.findByUsername(username);
        if (byUsername.isPresent()) {
            Credentials credentials = byUsername.get();
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(credentials.getRole().toString()));
            return new org.springframework.security.core.userdetails.User(
                    credentials.getUsername(), credentials.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("No user found");
        }
    }
}
