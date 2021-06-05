package spittr.security.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

   private final RestTemplate restTemplate;
   private final String dataBaseUsr;

    public UserDetailsServiceImpl(RestTemplate restTemplate,
                                  @Value("${api.database.url:http://localhost:8081/data/users}") String dataBaseUsr) {
        this.restTemplate = restTemplate;
        this.dataBaseUsr = dataBaseUsr;
    }

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        SystemUser systemUser = restTemplate.getForObject(dataBaseUsr + "/{userName}", SystemUser.class, userName);
        log.info("Security got system user [{}]",systemUser);
        GrantedAuthority authority = new SimpleGrantedAuthority(systemUser.getAuthority());
        UserDetails userDetails = new User(systemUser.getUserName(),
                systemUser.getPassword(), Arrays.asList(authority));
        return userDetails;
    }
}