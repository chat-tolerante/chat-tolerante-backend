package com.chat.app.rest.Repository;

import com.chat.app.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository <User, Long> {
    User findByEmail(String email);
}
