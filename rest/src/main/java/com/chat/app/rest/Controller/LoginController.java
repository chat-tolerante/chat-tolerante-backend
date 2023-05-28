package com.chat.app.rest.Controller;

import com.chat.app.rest.Exception.UserUnauthorizedException;
import com.chat.app.rest.Extras.UserNoPassword;
import com.chat.app.rest.Model.User;
import com.chat.app.rest.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public UserNoPassword register(@RequestBody User user, HttpSession session) {
        User created = this.userRepository.save(user);
        UserNoPassword filteredUser = new UserNoPassword(created);
        session.setAttribute("user", filteredUser);
        return filteredUser;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public User saveLogin(@RequestBody User user, HttpSession session) {
        String email = user.getEmail();
        String password = user.getPassword();

        User storedUser = userRepository.findByEmail(email);
        if (storedUser == null || !storedUser.getPassword().equals(password)) {
            throw new UserUnauthorizedException();
        }
        session.setAttribute("user", new UserNoPassword(storedUser));
        return storedUser;
    }

    @GetMapping("/user")
    @ResponseBody
    public UserNoPassword getUser(HttpSession session) {
        UserNoPassword authenticated = (UserNoPassword) session.getAttribute("user");
        if (authenticated == null) {
            throw new UserUnauthorizedException();
        }
        return authenticated;
    }

    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @GetMapping("/email-in-use")
    public boolean isEmailInUse(@RequestParam("email") String email) {
        User found = this.userRepository.findByEmail(email);
        if (found == null) {
            return false;
        }
        return true;
    }
}
