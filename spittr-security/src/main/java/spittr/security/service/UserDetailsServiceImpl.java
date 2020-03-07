package spittr.security.service;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spittr.domain.model.SystemUser;

import java.util.Arrays;

@Slf4j
@Service
public class UserDetailsServiceImpl
        implements UserDetailsService {

   @Autowired
   RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        SystemUser systemUser = restTemplate.getForObject("http://localhost:8080/data/users/{userName}", SystemUser.class, userName);
        log.info("Security got system user [{}]",systemUser);
        GrantedAuthority authority = new SimpleGrantedAuthority(systemUser.getAuthority());
        UserDetails userDetails = new User(systemUser.getUserName(),
                systemUser.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}