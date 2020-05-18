package com.cihan.springexamples.springsecurityjwt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private Map<String, String> users = new HashMap<>();

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        users.put("cihan", passwordEncoder.encode("1234"));
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        // Db kontrol'u
        if(users.containsKey(userName)){
            return new User(userName, users.get(userName), new ArrayList<>());
        }
        throw new UsernameNotFoundException(userName);
    }
}
