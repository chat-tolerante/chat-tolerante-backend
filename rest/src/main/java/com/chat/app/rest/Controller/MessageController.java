package com.chat.app.rest.Controller;

import com.chat.app.rest.Exception.UserUnauthorizedException;
import com.chat.app.rest.Extras.NewMessage;
import com.chat.app.rest.Extras.UserNoPassword;
import com.chat.app.rest.Model.Message;
import com.chat.app.rest.Model.User;
import com.chat.app.rest.Repository.MessageRepository;
import com.chat.app.rest.Repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/messages/{with}")
    @ResponseBody
    public List<Message> getMessages(@PathVariable("with") long userId, HttpSession session) {
        UserNoPassword authenticated = (UserNoPassword) session.getAttribute("user");
        if(authenticated == null){
            throw new UserUnauthorizedException();
        }
        User otherUser = new User();
        otherUser.setId(userId);
        List<Message> received = messageRepository.findBySenderAndReceiver(otherUser, authenticated.toUser());
        List<Message> sent = messageRepository.findBySenderAndReceiver(authenticated.toUser(), otherUser);
        received.addAll(sent);
        Collections.sort(received);
        sent.forEach(message -> {
            message.setReceiver(new UserNoPassword(message.getReceiver()).toUser());
            message.setSender(new UserNoPassword(message.getSender()).toUser());
        });
        return received;
    }

    @PostMapping(value = "/message")
    @ResponseBody
    public Message saveMessage(@RequestBody NewMessage body, HttpSession session) {
        UserNoPassword authenticated = (UserNoPassword) session.getAttribute("user");
        if (authenticated == null) {
            throw new UserUnauthorizedException();
        }
        User user = new User();
        user.setId(body.sendTo);
        Message message = new Message();
        message.setText(body.message);
        message.setReceiver(user);
        message.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        message.setSender(authenticated.toUser());
        return messageRepository.save(message);
    }
}
