package com.chat.app.rest.Controller;

import com.chat.app.rest.Model.Message;
import com.chat.app.rest.Model.User;
import com.chat.app.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;


    @GetMapping(value = "/")
    public String holaMundo() {
        return "HOLA MUNDO!!!";
        }
    @GetMapping(value= "/users")
    public List <User> getUsers(){
        return todoRepository.findAll();
    }
    @PostMapping(value = "/saveuser")
    public String saveUser(@RequestBody User user){
        todoRepository.save(user);
        return "Saved user";
    }
    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = todoRepository.findById(id).get();
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        todoRepository.save(updatedUser);
        return "Updated User";
    }
    @DeleteMapping(value = "delete/{id}")
    public String deleteUser(@PathVariable long id){
        User deletedUser = todoRepository.findById(id).get();
        todoRepository.delete(deletedUser);
        return "Deleted User";
    }

}