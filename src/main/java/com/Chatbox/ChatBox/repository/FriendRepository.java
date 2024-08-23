package com.Chatbox.ChatBox.repository;

import com.Chatbox.ChatBox.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friends,Long> {

}
