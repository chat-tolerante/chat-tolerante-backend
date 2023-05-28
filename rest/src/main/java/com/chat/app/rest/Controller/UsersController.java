package com.chat.app.rest.Controller;

import com.chat.app.rest.Exception.UserUnauthorizedException;
import com.chat.app.rest.Extras.UserNoPassword;
import com.chat.app.rest.Model.User;
import com.chat.app.rest.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/")
    public String holaMundo() {
        return "HOLA MUNDO!!!";
        }
    @GetMapping(value= "/users")
    @ResponseBody
    public List <User> getUsers(HttpSession session){
        UserNoPassword authenticated = (UserNoPassword) session.getAttribute("user");
        if(authenticated == null){
            throw new UserUnauthorizedException();
        }
        return this.userRepository.findAllExcept(authenticated.id);
    }

}