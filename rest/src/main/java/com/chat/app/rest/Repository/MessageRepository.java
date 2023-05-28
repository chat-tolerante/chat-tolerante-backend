package com.chat.app.rest.Repository;

import com.chat.app.rest.Model.Message;
import com.chat.app.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository <Message, Long> {
    List<Message> findBySenderAndReceiver(User sender, User receiver);
}
