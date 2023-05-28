package com.chat.app.rest.Repository;

import com.chat.app.rest.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query(value = "SELECT u FROM User u WHERE u.id <> ?1")
    List<User> findAllExcept(long id);
}
