package com.chat.app.rest.Controller;

import com.chat.app.rest.Model.Message;
import com.chat.app.rest.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "/messages")
    public List <Message> getMessages(){
        return messageRepository.findAll();
    }

    @PostMapping(value = "/savemessage")
    public String saveMessage(@RequestBody Message message){
        messageRepository.save(message);
        return "Saved Message";
    }
    @PutMapping(value = "/updatemessage/{id}")
    public String updateMessage(@PathVariable long id, @RequestBody Message message){
        Message updatedMessage = messageRepository.findById(id).get();
        updatedMessage.setSender(message.getSender());
        updatedMessage.setReceiver(message.getReceiver());
        updatedMessage.setText(message.getText());
        updatedMessage.setCreatedDate(message.getCreatedDate());
        messageRepository.save(updatedMessage);
        return "Updated Message";

    }
    @DeleteMapping(value = "deletemessage/{id}")
    public String deleteMessage(@PathVariable long id){
        Message deletedMessage = messageRepository.findById(id).get();
        messageRepository.delete(deletedMessage);
        return "Deleted Message";
    }
}
