package com.chat.app.rest.Controller;

import com.chat.app.rest.Model.User;
import com.chat.app.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping//("/Login")
public class LoginController {

    private final TodoRepository todoRepository;

    @Autowired
    public LoginController(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public User register(@RequestBody User user){
        User created = this.todoRepository.save(user);
        created.setPassword(null);
        return created;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public User saveLogin(@RequestBody User user){
        String email = user.getEmail();
        String password = user.getPassword();

        User storedUser = todoRepository.findByEmail(email);

        if (storedUser != null && storedUser.getPassword().equals(password)){
            return storedUser;
        }
        throw new Unau;
    }


}
