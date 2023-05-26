package com.chat.app.rest.Controller;

import com.chat.app.rest.Model.User;
import com.chat.app.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping//("/Login")
public class LoginController {

    private final TodoRepository todoRepository;

    @Autowired
    public LoginController(TodoRepository todoRepository){
        this.todoRepository = todoRepository;
    }

    @PostMapping(value = "/login")
    public String saveLogin(@RequestBody User user){
        String email = user.getEmail();
        String password = user.getPassword();

        User storedUser = todoRepository.findByEmail(email);

        if (storedUser != null && storedUser.getPassword().equals(password)){
            return "Succesful Login " + storedUser.getName();
        } else {
            return "Invalid!";
        }
    }


}
