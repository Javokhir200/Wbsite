package uz.pdp.wbsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import uz.pdp.wbsite.dao.UserDao;
import uz.pdp.wbsite.model.Person;

@Controller
public class AuthController {
    @Autowired
    UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    String getLoginPage(){
        return "login";
    }
    @GetMapping("/logout")
    public String getLogoutPage(){
        return "logout";
    }
    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }
    @PostMapping("/signup")
    public String getUserData(@ModelAttribute Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return "/home";
    }
}
