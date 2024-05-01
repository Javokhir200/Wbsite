package uz.pdp.wbsite.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.wbsite.dao.UserDao;
import uz.pdp.wbsite.model.Person;

import java.util.ArrayList;

@Component("UserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = userDao.getByUsername(username);
        return new User(user.getUsername(),user.getPassword(),new ArrayList<>());
    }
}
