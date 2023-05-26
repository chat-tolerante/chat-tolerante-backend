package com.chat.app.rest.Repository;

import com.chat.app.rest.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository <Message, Long> {
}
