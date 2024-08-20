package com.Chatbox.ChatBox.repository;

import com.Chatbox.ChatBox.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByUserEmail(String email);
}
